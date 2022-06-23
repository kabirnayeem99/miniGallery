package io.github.kabirnayeem99.minigallery.domain.repositories

import io.github.kabirnayeem99.minigallery.domain.entity.Image
import kotlinx.coroutines.flow.Flow

interface FolderImageRepository {
    suspend fun getAllImageByPath(folderPath: String): Flow<List<Image>>
}