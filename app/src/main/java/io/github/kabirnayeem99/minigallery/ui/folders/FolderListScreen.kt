package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nesyou.staggeredgrid.LazyStaggeredGrid
import com.nesyou.staggeredgrid.StaggeredCells
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import io.github.kabirnayeem99.minigallery.ui.common.PageTransitionAnimation
import io.github.kabirnayeem99.minigallery.ui.destinations.FolderImageListScreenDestination


@RootNavGraph(true)
@Destination(style = PageTransitionAnimation::class)
@Composable
fun FolderListScreen(
    navigator: DestinationsNavigator,
    folderViewModel: FolderViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        folderViewModel.fetchFolderList()
    }

    val uiState = folderViewModel.uiState

    FolderListScreenContent(uiState = uiState, navigator = navigator)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FolderListScreenContent(uiState: FolderUiState, navigator: DestinationsNavigator) {
    Scaffold {
        it.toString()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding() + 12.dp)
        ) {

            FolderLoadingIndicator(isLoading = uiState.isLoading)

            FolderGridList(
                modifier = Modifier.padding(8.dp),
                folderList = uiState.folderList,
                onFolderItemClick = { folder ->
                    navigator.navigate(
                        FolderImageListScreenDestination(
                            folder.path,
                            folder.folderName
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun FolderGridList(
    modifier: Modifier = Modifier,
    folderList: List<ImageFolder> = emptyList(),
    onFolderItemClick: (ImageFolder) -> Unit = {}
) {
    LazyStaggeredGrid(
        modifier = modifier,
        cells = StaggeredCells.Adaptive(minSize = 180.dp)
    ) {
        items(folderList) { folder ->
            FolderItem(
                folder = folder,
                onFolderClick = {
                    onFolderItemClick(folder)
                },
            )
        }
    }
}

