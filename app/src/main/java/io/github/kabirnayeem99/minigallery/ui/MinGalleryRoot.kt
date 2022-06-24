package io.github.kabirnayeem99.minigallery.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import io.github.kabirnayeem99.minigallery.ui.theme.MiniGalleryTheme

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MinGalleryRoot() {
    MiniGalleryTheme {
        val systemUiController = rememberSystemUiController()
        val shouldUseDarkIcon = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = shouldUseDarkIcon)
            systemUiController.setNavigationBarColor(
                Color.Transparent,
                darkIcons = shouldUseDarkIcon
            )
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = shouldUseDarkIcon)
        }

        val navController = rememberAnimatedNavController()
        val navHostEngine = rememberAnimatedNavHostEngine()

        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = navController,
            engine = navHostEngine
        )
    }
}