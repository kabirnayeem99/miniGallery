package io.github.kabirnayeem99.minigallery.ui.allImages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kabirnayeem99.minigallery.ui.destinations.ImageViewScreenDestination
import io.github.kabirnayeem99.minigallery.ui.folderImages.FolderImageItem
import timber.log.Timber


const val PICTURE_LIST_TAB_ID = "picture_list_tab_1028"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllImagesTab(
    navigator: DestinationsNavigator,
    viewModel: AllImagesViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.fetchAllImages()
    }

    val uiState = viewModel.uiState

    Scaffold {
        it.toString()
        Column {
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 12.dp),
                columns = GridCells.Adaptive(minSize = 180.dp),
            ) {
                items(uiState.imagesList.size) { index ->
                    val image = uiState.imagesList[index]
                    FolderImageItem(image = image) {
                        navigator.navigate(ImageViewScreenDestination(image.name, image.path))
                    }
                }
            }

            Timber.d(uiState.toString())

        }
    }
}