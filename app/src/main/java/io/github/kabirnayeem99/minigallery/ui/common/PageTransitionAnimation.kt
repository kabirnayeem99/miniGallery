package io.github.kabirnayeem99.minigallery.ui.common

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

const val ANIMATION_SPEC_TWEEN_DURATION = 450

@OptIn(ExperimentalAnimationApi::class)
object PageTransitionAnimation : DestinationStyle.Animated {

    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { 1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeIn(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )

    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { -1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeOut(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )

    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { -1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeIn(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )

    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { 1080 },
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        ) + fadeOut(
            animationSpec = tween(ANIMATION_SPEC_TWEEN_DURATION)
        )

    }
}