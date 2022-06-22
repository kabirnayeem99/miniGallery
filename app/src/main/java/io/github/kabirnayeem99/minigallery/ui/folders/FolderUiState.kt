package io.github.kabirnayeem99.minigallery.ui.folders

import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder

data class FolderUiState(
    val isLoading: Boolean = false,
    val folderList: List<ImageFolder> = emptyList(),
)