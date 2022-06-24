package io.github.kabirnayeem99.minigallery.ui.image

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.ui.common.PageTransitionAnimation
import io.github.kabirnayeem99.minigallery.ui.common.TopAppBarWithNavigation
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Destination(style = PageTransitionAnimation::class)
@Composable
fun ImageViewScreen(imageName: String, imagePath: String, navigator: DestinationsNavigator) {
    Scaffold(topBar = {
        TopAppBarWithNavigation(
            startIcon = Icons.Outlined.ArrowBack,
            startIconContentDescriptor = stringResource(id = R.string.content_desc_back),
            titleText = imageName,
            startIconClickListener = {
                navigator.navigateUp()
            },
            endIconClickListener = {
                Timber.d("Clicked on share button.")
            }
        )
    }) {
        it.toString()
        GlideImage(
            imageModel = imagePath,
            contentScale = ContentScale.Crop,
        )
    }
}