package io.github.kabirnayeem99.minigallery.ui.folderImages

import io.github.kabirnayeem99.minigallery.domain.entity.Image

data class FolderImageUiState(
    val imageList: List<Image> = emptyList(),
    val isLoading: Boolean = false,
)