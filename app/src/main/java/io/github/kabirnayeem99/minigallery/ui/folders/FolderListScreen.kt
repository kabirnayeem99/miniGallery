package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun FolderListScreen(folderViewModel: FolderViewModel) {

    LaunchedEffect(true) {
        folderViewModel.fetchFolderList()
    }

    val uiState = folderViewModel.uiState

    LazyColumn {

        item {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
        }

        itemsIndexed(uiState.folderList) { item, index ->
            Text(text = index + item)
        }
    }

}