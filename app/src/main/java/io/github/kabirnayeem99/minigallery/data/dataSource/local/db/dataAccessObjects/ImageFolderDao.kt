package io.github.kabirnayeem99.minigallery.data.dataSource.local.db.dataAccessObjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.kabirnayeem99.minigallery.data.dto.local.ImageFolderEntity

@Dao
interface ImageFolderDao {

    @Query("SELECT * FROM imagefolderentity")
    suspend fun getAllImageFolders(): List<ImageFolderEntity>

    @Insert
    fun insertAll(allFolders: List<ImageFolderEntity>)

}