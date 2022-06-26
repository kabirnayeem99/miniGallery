package io.github.kabirnayeem99.minigallery.data.repositories

import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.repositories.AllImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AllImageRepositoryImpl(private val dataSource: MediaDataSource) : AllImageRepository {

    override suspend fun getAllImages(): Flow<List<Image>> {
        return flow {
            val list = dataSource.getAllImages()
            emit(list)
        }
    }
}