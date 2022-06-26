package io.github.kabirnayeem99.minigallery.data.repositories

import io.github.kabirnayeem99.minigallery.data.dataSource.local.CachingDataSource
import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.data.dto.local.AllImageEntity
import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.repositories.AllImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class AllImageRepositoryImpl(
    private val dataSource: MediaDataSource,
    private val cachingDataSource: CachingDataSource,
) : AllImageRepository {


    private val imageMemoryLock = Mutex()
    private var inMemoryCachedImages = listOf<Image>()

    override suspend fun getAllImages(): Flow<List<Image>> {

        val cachedImages = getInMemoryCacheAllImages()

        return flow {

            val locallySavedImages = cachingDataSource.getAllImages().map {
                Image(
                    name = it.name, path = it.path, size = it.size,
                    thumbnail = it.thumbnail
                )
            }

            if (locallySavedImages.size != cachedImages.size) {
                emit(locallySavedImages)
            }

            val list = dataSource.getAllImages()
            if (locallySavedImages.size != list.size && list.size != cachedImages.size) {
                cacheAllImages(list)
                emit(list)
            }
        }.onStart {
            if (cachedImages.isNotEmpty()) emit(cachedImages)
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getInMemoryCacheAllImages(): List<Image> {
        return imageMemoryLock.withLock {
            inMemoryCachedImages
        }
    }

    private suspend fun cacheAllImages(images: List<Image>) {
        withContext(Dispatchers.IO) {
            imageMemoryLock.withLock {
                inMemoryCachedImages = images
            }

            cachingDataSource.cacheAllImages(images.map {
                AllImageEntity(
                    name = it.name, path = it.path, size = it.size,
                    thumbnail = it.thumbnail
                )
            })
        }
    }
}