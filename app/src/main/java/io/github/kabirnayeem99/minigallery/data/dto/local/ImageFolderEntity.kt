package io.github.kabirnayeem99.minigallery.data.dto.local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageFolderEntity(
    @PrimaryKey
    var path: String = "",
    @ColumnInfo(name = "folder_name")
    var folderName: String = "",
    @ColumnInfo(name = "number_of_pictures")
    var numberOfPictures: Int = 0,
    @ColumnInfo(name = "thumbnail")
    var thumbnail: Bitmap? = null,
    @ColumnInfo(name = "size")
    var size: String = "0 KB",
)