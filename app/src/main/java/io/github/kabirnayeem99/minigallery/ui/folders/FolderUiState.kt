package io.github.kabirnayeem99.minigallery.ui.folders

data class FolderUiState(
    val isLoading: Boolean = false,
    val folderList: List<String> = emptyList(),
)