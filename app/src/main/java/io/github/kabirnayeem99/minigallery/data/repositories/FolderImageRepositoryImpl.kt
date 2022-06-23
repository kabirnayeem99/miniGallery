package io.github.kabirnayeem99.minigallery.data.repositories

import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FolderImageRepositoryImpl @Inject constructor(private val dataSource: MediaDataSource) :
    FolderImageRepository {

    private val imageInMemoryLock = Mutex()
    private var inMemoryCacheImageList = emptyList<Image>()

    override suspend fun getAllImageByPath(folderPath: String): Flow<List<Image>> {

        val cachedImageList = getCachedInMemoryImageList()

        return flow {
            val imageList = dataSource.getImagesForFolderPath(folderPath)
            cacheImageListInMemory(imageList)
            emit(imageList)
        }.onStart {
            emit(cachedImageList)
        }.flowOn(Dispatchers.IO)

    }


    /**
     * Takes a list of Images, and then it caches that list in memory
     *
     * @param imageList List<Image>
     */
    private suspend fun cacheImageListInMemory(imageList: List<Image>) {
        withContext(Dispatchers.IO) {
            imageInMemoryLock.withLock {
                inMemoryCacheImageList = imageList
            }
        }
    }

    /**
     * Returns the cached image list from memory
     *
     * @return A list of Image objects.
     */
    private suspend fun getCachedInMemoryImageList(): List<Image> {
        return imageInMemoryLock.withLock { inMemoryCacheImageList }
    }
}