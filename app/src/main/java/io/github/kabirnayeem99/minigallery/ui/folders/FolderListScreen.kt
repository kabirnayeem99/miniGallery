package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import io.github.kabirnayeem99.minigallery.ui.common.PageTransitionAnimation
import io.github.kabirnayeem99.minigallery.ui.common.TopAppBarWithNavigation
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
    Scaffold(
        topBar = {
            TopAppBarWithNavigation(
                startIcon = Icons.Outlined.Settings,
                startIconContentDescriptor = stringResource(id = R.string.content_desc_settings),
                endIcon = Icons.Outlined.Info,
                endIconContentDescriptor = stringResource(id = R.string.content_desc_info),
                titleText = stringResource(id = R.string.app_name)
            )
        }) {
        it.toString()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            FolderGridList(
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
    onFolderItemClick: (ImageFolder) -> Unit = {},
) {
    LazyVerticalGrid(
        modifier = modifier.padding(start = 8.dp),
        columns = GridCells.Adaptive(minSize = 180.dp)
    ) {
        items(folderList.size) { index ->
            val folder = folderList[index]
            FolderItem(
                folder = folder,
                onFolderClick = {
                    onFolderItemClick(folder)
                },
            )
        }
    }
}

