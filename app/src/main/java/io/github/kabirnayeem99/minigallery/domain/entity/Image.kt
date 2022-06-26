package io.github.kabirnayeem99.minigallery.domain.entity

import android.graphics.Bitmap

/**
 * Data class for image.
 *
 * @property {String} name - The name of the image.
 * @property {String} path - The path of the image on the device.
 * @property {String} size - The size of the image in bytes.
 * @property {String} uri - The path to the image file.
 * @property {Boolean} isSelected - This is a boolean property that will be used to determine if the
 * image is selected or not.
 */
data class Image(
    var name: String = "",
    var path: String = "",
    var size: Int = 0,
    var uri: String = "",
    var isSelected: Boolean = false,
    var thumbnail: Bitmap? = null,
)
