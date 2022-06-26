package io.github.kabirnayeem99.minigallery.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.kabirnayeem99.minigallery.data.dataSource.local.db.dataAccessObjects.ImageFolderDao
import io.github.kabirnayeem99.minigallery.data.dto.local.ImageFolderEntity
import io.github.kabirnayeem99.minigallery.data.dto.local.typeConverter.ImageBitmapToStringAndStringToImageBitmap
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder

@Database(entities = [ImageFolderEntity::class], version = 1)
@TypeConverters(ImageBitmapToStringAndStringToImageBitmap::class)
abstract class MinGalleryCachingDatabase : RoomDatabase() {
    abstract fun imageFolderDao(): ImageFolderDao
}