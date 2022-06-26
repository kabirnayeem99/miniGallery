package io.github.kabirnayeem99.minigallery.data.dataSource.local

import io.github.kabirnayeem99.minigallery.data.dataSource.local.db.MinGalleryCachingDatabase
import io.github.kabirnayeem99.minigallery.data.dto.local.AllImageEntity
import io.github.kabirnayeem99.minigallery.data.dto.local.ImageFolderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CachingDataSource @Inject constructor(
    private val db: MinGalleryCachingDatabase
) {

    suspend fun cacheAllFolderImages(folders: List<ImageFolderEntity>) {
        withContext(Dispatchers.IO) {
            db.imageFolderDao().insertAll(folders)
        }
    }

    suspend fun getAllFolderImages(): List<ImageFolderEntity> {
        return withContext(Dispatchers.IO) {
            db.imageFolderDao().getAllImageFolders()
        }
    }

    suspend fun cacheAllImages(images: List<AllImageEntity>) {
        withContext(Dispatchers.IO) {
            db.allImageDao().insertAllImages(images)
        }
    }

    suspend fun getAllImages(): List<AllImageEntity> {
        return withContext(Dispatchers.IO) {
            db.allImageDao().getAllImages()
        }
    }
}