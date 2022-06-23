package io.github.kabirnayeem99.minigallery.ui.folderImages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nesyou.staggeredgrid.LazyStaggeredGrid
import com.nesyou.staggeredgrid.StaggeredCells
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage
import io.github.kabirnayeem99.minigallery.ui.common.PageTransitionAnimation
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(modifier = Modifier.fillMaxSize()) {
        it.toString()

        val imageList = uiState.imageList

        LazyStaggeredGrid(
            cells = StaggeredCells.Adaptive(minSize = 180.dp)
        ) {
            items(imageList) { image ->
                GlideImage(
                    imageModel = image.path,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Timber.d(uiState.imageList.toString())
    }
}