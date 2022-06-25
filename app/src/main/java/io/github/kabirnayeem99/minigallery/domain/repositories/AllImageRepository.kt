package io.github.kabirnayeem99.minigallery.domain.repositories

import io.github.kabirnayeem99.minigallery.domain.entity.Image
import kotlinx.coroutines.flow.Flow

interface AllImageRepository {
    suspend fun getAllImages(): Flow<List<Image>>

}