package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import io.github.kabirnayeem99.minigallery.ui.destinations.FolderImageListScreenDestination

const val FOLDER_LIST_TAB_ID = "folder_list_tab_845"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderListTab(
    navigator: DestinationsNavigator,
    folderViewModel: FolderViewModel = hiltViewModel()
) {

    val uiState = folderViewModel.uiState

    Scaffold {
        it.calculateTopPadding()
        FolderListScreenContent(uiState = uiState, navigator = navigator)
    }
}


@Composable
private fun FolderListScreenContent(uiState: FolderUiState, navigator: DestinationsNavigator) {


    Column(modifier = Modifier.fillMaxSize()) {

        ActionButtonGrid(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 18.dp)
                .fillMaxWidth()
        )

        FilterButton(
            modifier = Modifier
                .align(Alignment.End)
        )

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

@Composable
private fun FilterButton(modifier: Modifier = Modifier) {
    var shouldShowFilterMenu by remember {
        mutableStateOf(false)
    }

    var selectedMenu by remember {
        mutableStateOf(
            MenuItem(
                id = SortMenuItemAction.RECENT,
                title = "Recent",
            )
        )
    }

    val sortMenuItems = listOf(
        MenuItem(
            id = SortMenuItemAction.RECENT,
            title = "Recent",
        ),
        MenuItem(
            id = SortMenuItemAction.NAME_A_Z,
            title = "Name (a-z)",
        ),
        MenuItem(
            id = SortMenuItemAction.NAME_Z_A,
            title = "Name (z-a)",
        ),
        MenuItem(
            id = SortMenuItemAction.MODIFIED,
            title = "Modified",
        ),
        MenuItem(
            id = SortMenuItemAction.SIZE,
            title = "Size",
        )
    )

    Row(modifier = modifier.padding(end = 12.dp)) {
        TextButton(
            onClick = {
                shouldShowFilterMenu = true
            },
        ) {
            Text(
                text = selectedMenu.title,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                ),
                modifier = Modifier.padding(end = 12.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Outlined.FilterList, "", tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(16.dp)
                    .width(16.dp)
            )
        }

        DropdownMenu(
            expanded = shouldShowFilterMenu,
            modifier = Modifier.width(140.dp),
            onDismissRequest = { shouldShowFilterMenu = false }) {

            sortMenuItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            item.title,
                            color = if (item.id == selectedMenu.id)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onBackground,
                            fontWeight = if (item.id == selectedMenu.id)
                                FontWeight.Bold
                            else FontWeight.Normal
                        )
                    },
                    onClick = {
                        shouldShowFilterMenu = false
                        selectedMenu = item
                    },
                    leadingIcon = {
                        if (selectedMenu.id == item.id)
                            Icon(
                                Icons.Outlined.Done,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(0.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        else Box(modifier = Modifier)
                    },

                    )
            }
        }
    }
}

@Composable
private fun ActionButtonGrid(modifier: Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        columns = GridCells.Adaptive(minSize = 180.dp)
    ) {
        item {
            ActionButton(
                icon = Icons.Outlined.FavoriteBorder,
                title = stringResource(id = R.string.label_favourite)
            )
        }

        item {
            ActionButton(
                icon = Icons.Outlined.Delete,
                title = stringResource(id = R.string.label_bin)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActionButton(
    title: String,
    icon: ImageVector,
) {
    Card(
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.15F)
        ),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 18.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                icon,
                "",
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(text = title)
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
        modifier = modifier.padding(horizontal = 8.dp),
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


