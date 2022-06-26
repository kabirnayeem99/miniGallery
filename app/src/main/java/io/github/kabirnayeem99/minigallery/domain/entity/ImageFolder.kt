package io.github.kabirnayeem99.minigallery.domain.entity

import android.graphics.Bitmap

/**
 * Data class that holds the path to a folder, the name of the folder, the number of pictures in
 * the folder, and the path to the thumbnail of the folder.
 *
 * @property {String} path - The path to the folder.
 * @property {String} folderName - The name of the folder.
 * @property {Int} numberOfPictures - This is the number of pictures in the folder.
 * @property {String} thumbnailPath - This is the path to the thumbnail of the first image in the
 * folder.
 * @property {String} size - the size of the folder in string
 */
data class ImageFolder(
    var path: String = "",
    var folderName: String = "",
    var thumbnail: Bitmap? = null,
    var numberOfPictures: Int = 0,
    var thumbnailPath: String = "",
    var size: String = "0 KB",
) {

    /**
     * Increments the number of pictures by one
     */
    fun incrementPictureCount() {
        this.numberOfPictures++
    }
}
