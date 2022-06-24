package io.github.kabirnayeem99.minigallery.ui.common

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

const val ANIMATION_SPEC_TWEEN_DURATION = 450

@OptIn(ExperimentalAnimationApi::class)
object PageTransitionAnimation : DestinationStyle.Animated {

    /**
     * "Slide in from the right and fade in at the same time."
     *
     * The `slideInHorizontally` function takes an initial offset and an animation spec. The initial
     * offset is the starting position of the animation. In this case, we're starting the animation at
     * 1080 pixels to the right of the screen. The animation spec is the duration of the animation. In
     * this case, we're using the `ANIMATION_SPEC_TWEEN_DURATION` constant
     *
     * @return A transition that slides in from the right and fades in.
     */
    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { 1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeIn(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )
    }

    /**
     * `slideOutHorizontally(targetOffsetX = { -1080 }, animationSpec =
     * tween(ANIMATION_SPEC_TWEEN_DURATION)) + fadeOut(animationSpec =
     * tween(ANIMATION_SPEC_TWEEN_DURATION))`
     *
     * The above function is a combination of two animations:
     *
     * 1. `slideOutHorizontally(targetOffsetX = { -1080 }, animationSpec =
     * tween(ANIMATION_SPEC_TWEEN_DURATION))`
     * 2. `fadeOut(animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION))`
     *
     * The first animation is a slide out animation that slides the view out to the left. The second
     * animation is a fade out animation that fades the view out
     *
     * @return A transition that slides out horizontally and fades out.
     */
    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { -1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeOut(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )

    }

    /**
     * > The `popEnterTransition` function returns an `EnterTransition` object that slides in the new
     * screen from the left and fades it in
     *
     * @return A transition that slides in from the left and fades in.
     */
    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { -1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeIn(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )

    }

    /**
     * > The pop exit transition is a slide out horizontally animation with a target offset of 1080
     * pixels and a fade out animation
     *
     * @return A transition that slides out horizontally and fades out.
     */
    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { 1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeOut(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )

    }
}