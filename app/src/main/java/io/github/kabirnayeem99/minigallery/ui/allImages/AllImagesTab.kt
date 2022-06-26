package io.github.kabirnayeem99.minigallery.ui.allImages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
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

    val uiState = viewModel.uiState

    Scaffold {
        Column(
            modifier = Modifier.padding(
                start = it.calculateStartPadding(LayoutDirection.Ltr) + 12.dp,
                end = it.calculateEndPadding(LayoutDirection.Ltr) + 12.dp
            )
        ) {
            LazyVerticalGrid(
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