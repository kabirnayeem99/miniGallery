package io.github.kabirnayeem99.minigallery.data.repositories

import io.github.kabirnayeem99.minigallery.data.dataSource.local.CachingDataSource
import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.data.dto.local.ImageFolderEntity
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val dataSource: MediaDataSource,
    private val cachingDataSource: CachingDataSource,
) : FolderRepository {

    private val folderInMemoryLock = Mutex()
    private var inMemoryCacheFolderList = emptyList<ImageFolder>()

    /**
     * Fetches the folders with images and returns them
     *
     * @return a flow of folder's list
     */
    override suspend fun provideFolderList(): Flow<List<ImageFolder>> {
        val cachedFolderList = getCachedInMemoryFolderList()
        return flow {
            val localFolderList = getCachedFolderList()
            if (cachedFolderList.size != localFolderList.size)
                emit(localFolderList)

            val folderList = dataSource.getFolderWithPicturesFromTheDevice()
            if (localFolderList.size != folderList.size) {
                cacheFolderList(folderList)
                emit(folderList)
            }
        }.onStart {
            if (cachedFolderList.isNotEmpty())
                emit(cachedFolderList)
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Takes a list of ImageFolder objects, and then it caches that list in memory
     *
     * @param folderList List<ImageFolder>
     */
    private suspend fun cacheFolderList(folderList: List<ImageFolder>) {
        withContext(Dispatchers.IO) {
            folderInMemoryLock.withLock {
                inMemoryCacheFolderList = folderList
            }
            cachingDataSource.cacheAllFolderImages(folderList.map {
                ImageFolderEntity(
                    path = it.path,
                    folderName = it.folderName,
                    numberOfPictures = it.numberOfPictures,
                    thumbnail = it.thumbnail,
                    size = it.size
                )
            })
        }
    }

    /**
     * Returns the cached folder list from memory
     *
     * @return A list of ImageFolder objects.
     */
    private suspend fun getCachedInMemoryFolderList(): List<ImageFolder> {
        return folderInMemoryLock.withLock { inMemoryCacheFolderList }
    }

    /**
     * Returns the cached folder list from local storage
     *
     * @return A list of ImageFolder objects.
     */
    private suspend fun getCachedFolderList(): List<ImageFolder> {
        return cachingDataSource.getAllFolderImages().map {
            ImageFolder(
                path = it.path,
                folderName = it.folderName,
                numberOfPictures = it.numberOfPictures,
                thumbnail = it.thumbnail,
                size = it.size
            )
        }
    }
}