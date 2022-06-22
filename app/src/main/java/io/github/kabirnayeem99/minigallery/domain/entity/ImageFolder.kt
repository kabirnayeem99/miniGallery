package io.github.kabirnayeem99.minigallery.domain.entity

data class ImageFolder(
    var path: String = "",
    var folderName: String = "",
    var numberOfPictures: Int = 0,
    var thumbnailPath: String = "",
) {
    fun addPictures() {
        this.numberOfPictures++
    }
}
