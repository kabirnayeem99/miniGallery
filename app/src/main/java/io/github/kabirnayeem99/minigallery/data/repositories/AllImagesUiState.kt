package io.github.kabirnayeem99.minigallery.data.repositories

import io.github.kabirnayeem99.minigallery.domain.entity.Image

data class AllImagesUiState(
   val imagesList: List<Image> = emptyList()
)