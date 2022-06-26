package io.github.kabirnayeem99.minigallery.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.kabirnayeem99.minigallery.data.dataSource.local.db.dataAccessObjects.AllImageDao
import io.github.kabirnayeem99.minigallery.data.dataSource.local.db.dataAccessObjects.ImageFolderDao
import io.github.kabirnayeem99.minigallery.data.dto.local.AllImageEntity
import io.github.kabirnayeem99.minigallery.data.dto.local.ImageFolderEntity
import io.github.kabirnayeem99.minigallery.data.dto.local.typeConverter.DateToLongAndLongToDateTypeConverter
import io.github.kabirnayeem99.minigallery.data.dto.local.typeConverter.ImageBitmapToStringAndStringToImageBitmap

@Database(entities = [ImageFolderEntity::class, AllImageEntity::class], version = 2)
@TypeConverters(
    ImageBitmapToStringAndStringToImageBitmap::class,
    DateToLongAndLongToDateTypeConverter::class
)
abstract class MinGalleryCachingDatabase : RoomDatabase() {
    abstract fun imageFolderDao(): ImageFolderDao
    abstract fun allImageDao(): AllImageDao
}