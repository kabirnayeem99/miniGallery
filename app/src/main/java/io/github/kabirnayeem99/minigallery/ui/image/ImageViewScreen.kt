package io.github.kabirnayeem99.minigallery.ui.image

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.ui.common.PageTransitionAnimation
import io.github.kabirnayeem99.minigallery.ui.common.TopAppBarWithNavigation
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Destination(style = PageTransitionAnimation::class)
@Composable
fun ImageViewScreen(imageName: String, imagePath: String, navigator: DestinationsNavigator) {

    val systemUiController = rememberSystemUiController()

    var shouldShowOtherUiElements by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        AnimatedVisibility(
            visible = shouldShowOtherUiElements,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut(),
        ) {
            TopAppBarWithNavigation(
                navigationIcon = Icons.Outlined.ArrowBack,
                navigationIconContentDescriptor = stringResource(id = R.string.content_desc_back),
                titleText = imageName,
                navigationIconClickListener = {
                    navigator.navigateUp()
                },
                menuIconClickListener = {
                    Timber.d("Clicked on share button.")
                }
            )
        }
    }) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            it.toString()

            GlideImage(
                modifier = Modifier.clickable {
                    scope.launch {
                        shouldShowOtherUiElements = !shouldShowOtherUiElements
                        systemUiController.isSystemBarsVisible =
                            !systemUiController.isSystemBarsVisible
                    }
                },
                imageModel = imagePath,
                contentScale = ContentScale.Fit,
            )

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 28.dp, start = 12.dp, end = 12.dp),
                visible = shouldShowOtherUiElements,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Row(

                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(Icons.Outlined.Share, "Share your photo.")
                    Icon(Icons.Outlined.Delete, "Delete your photo.")
                    Icon(Icons.Outlined.Edit, "Edit your photo.")
                }
            }
        }
    }
}