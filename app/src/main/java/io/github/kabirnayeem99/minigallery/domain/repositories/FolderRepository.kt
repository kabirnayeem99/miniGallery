package io.github.kabirnayeem99.minigallery.domain.repositories

import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {

    suspend fun provideFolderList(): Flow<List<ImageFolder>>
}