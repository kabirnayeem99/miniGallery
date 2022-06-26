package io.github.kabirnayeem99.minigallery.data.dataSource.local.db.dataAccessObjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.kabirnayeem99.minigallery.data.dto.local.AllImageEntity

@Dao
interface AllImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(images: List<AllImageEntity>)


    @Query("SELECT * FROM AllImageEntity")
    suspend fun getAllImages(): List<AllImageEntity>
}