package io.github.kabirnayeem99.minigallery.data.dto.local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllImageEntity(
    @ColumnInfo(name = "name")
    var name: String = "",
    @PrimaryKey
    var path: String = "",
    @ColumnInfo(name = "size")
    var size: Int = 0,
    @ColumnInfo(name = "thumbnail")
    var thumbnail: Bitmap? = null,
)