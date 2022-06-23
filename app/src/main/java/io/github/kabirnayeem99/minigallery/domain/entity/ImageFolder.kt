package io.github.kabirnayeem99.minigallery.domain.entity

/**
 * Data class that holds the path to a folder, the name of the folder, the number of pictures in
 * the folder, and the path to the thumbnail of the folder.
 *
 * @property {String} path - The path to the folder.
 * @property {String} folderName - The name of the folder.
 * @property {Int} numberOfPictures - This is the number of pictures in the folder.
 * @property {String} thumbnailPath - This is the path to the thumbnail of the first image in the
 * folder.
 */
data class ImageFolder(
    var path: String = "",
    var folderName: String = "",
    var numberOfPictures: Int = 0,
    var thumbnailPath: String = "",
) {

    /**
     * Increments the number of pictures by one
     */
    fun incrementPictureCount() {
        this.numberOfPictures++
    }
}
