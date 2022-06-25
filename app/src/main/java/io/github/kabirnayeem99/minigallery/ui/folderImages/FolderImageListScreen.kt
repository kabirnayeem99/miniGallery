package io.github.kabirnayeem99.minigallery.ui.folderImages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.ui.common.PageTransitionAnimation
import io.github.kabirnayeem99.minigallery.ui.common.TopAppBarWithNavigation
import io.github.kabirnayeem99.minigallery.ui.destinations.ImageViewScreenDestination
import timber.log.Timber

@Destination(style = PageTransitionAnimation::class)
@Composable
fun FolderImageListScreen(
    folderPath: String,
    folderName: String,
    navigator: DestinationsNavigator,
    folderImageListViewModel: FolderImageListViewModel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        folderImageListViewModel.fetchImagesByFolder(folderPath)
    }

    val uiState = folderImageListViewModel.uiState

    FolderImageListContent(folderName, navigator, uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FolderImageListContent(
    folderName: String,
    navigator: DestinationsNavigator,
    uiState: FolderImageUiState,
) {
    Scaffold(
        topBar = {
            TopAppBarWithNavigation(
                navigationIcon = Icons.Outlined.ArrowBack,
                navigationIconContentDescriptor = stringResource(id = R.string.content_desc_back),
                titleText = folderName,
                navigationIconClickListener = {
                    navigator.navigateUp()
                },
                menuIconClickListener = {
                    Timber.d("Clicked on share button.")
                }
            )
        },
    ) {

        it.toString()

        Column(modifier = Modifier.padding(top = 12.dp)) {

            val imageList = uiState.imageList

            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 12.dp),
                columns = GridCells.Adaptive(minSize = 180.dp),
                contentPadding = PaddingValues(top = 72.dp, bottom = 22.dp)
            ) {
                items(imageList.size) { index ->
                    val image = imageList[index]
                    FolderImageItem(image = image) {
                        navigator.navigate(ImageViewScreenDestination(image.name, image.path))
                    }
                }
            }
            Timber.d(uiState.imageList.toString())
        }
    }
}
