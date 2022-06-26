package io.github.kabirnayeem99.minigallery.data.dataSource.local

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import io.github.kabirnayeem99.minigallery.core.ktx.round
import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import kotlin.math.roundToInt


class MediaDataSource @Inject constructor(context: Context) {

    private val contentResolver = context.contentResolver


    /**
     * Gets all the images from the device and then groups them into folders
     *
     * @return the list of folders with images.
     */
    suspend fun getFolderWithPicturesFromTheDevice(): List<ImageFolder> {

        return withContext(Dispatchers.IO) {
            if (contentResolver == null) return@withContext emptyList()

            val folders = arrayListOf<ImageFolder>()
            val picturePaths = arrayListOf<String>()

            val allPicturesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.SIZE,
            )

            val cursor: Cursor? = contentResolver.query(
                allPicturesUri, projection,
                null,
                null,
                null,
            )

            var folderSize = 0.0

            try {
                cursor?.moveToFirst()

                do {

                    val folder = ImageFolder()

                    val bucketName = cursor?.let {
                        it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                    } ?: ""

                    val dataPath = cursor?.let {
                        it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    } ?: ""


                    val size = (((cursor?.let {
                        it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                    } ?: "0").toDoubleOrNull() ?: 0.0) / 1024.0)

                    folderSize += size

                    var folderPaths: String = dataPath.substring(
                        0, dataPath.lastIndexOf(
                            "$bucketName/"
                        )
                    )

                    folderPaths = "$folderPaths$bucketName/"


                    if (folderPaths.isNotBlank() && !picturePaths.contains(folderPaths)) {
                        picturePaths.add(folderPaths)
                        val thumbnail: Bitmap? = ThumbnailUtils.createImageThumbnail(
                            dataPath,
                            MediaStore.Images.Thumbnails.MINI_KIND
                        )
                        folder.path = folderPaths
                        folder.folderName = bucketName
                        folder.thumbnailPath = dataPath
                        folder.thumbnail = thumbnail
                        folder.incrementPictureCount()
                        folders.add(folder)
                    } else {
                        for (index in 0 until folders.size) {
                            val selectedFolder = folders[index]
                            if (selectedFolder.path == folderPaths) {
                                selectedFolder.thumbnailPath = dataPath
                                selectedFolder.incrementPictureCount()
                            }
                        }
                    }

                } while (cursor?.moveToNext() == true)
                cursor?.close()
            } catch (e: Exception) {
                Timber.e(e, "Failed to load picture folders -> ${e.message}")
            }

            Timber.d("Folders -> $folders\n")


            folders.map {
                var format = "KB"
                val file = File(it.path)
                var size = getFolderSize(file)
                if (size > 1024) {
                    size /= 1024
                    format = "MB"
                }
                if (size > 1024) {
                    format = "GB"
                    size /= 1024
                }
                val folder = it.copy(size = "${size.round(2)} $format")
                folder
            }.toList()
        }
    }

    private fun getFolderSize(folderPath: File): Float {

        var totalSize = 0F

        if (!folderPath.isDirectory) {
            Timber.tag("SIZE").e("Folder path is not a directory.")
            return 0.0F
        }

        val files = folderPath.listFiles() ?: arrayOf()

        if (files.isEmpty()) {
            Timber.e("Folder path is empty.")
            return 0.0F
        }

        for (file in files) {
            if (file.isFile) {
                totalSize += file.length()
            } else if (file.isDirectory) {
                totalSize += file.length()
                totalSize += getFolderSize(file)
            }
        }


        return (totalSize / 1024F).round(2)
    }

    /**
     * Gets all the images from the device and returns them in a list
     *
     * @return A list of images.
     */
    suspend fun getAllImages(): List<Image> {

        return withContext(Dispatchers.IO) {

            if (contentResolver == null) return@withContext emptyList()

            var images = arrayListOf<Image>()
            val allVideosUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.YEAR,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.DATE_MODIFIED,
            )

            val cursor = contentResolver.query(
                allVideosUri,
                projection,
                null,
                null
            )

            try {
                cursor?.moveToFirst()
                do {

                    val image = Image()

                    val imagePath = cursor?.let { c ->
                        c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    } ?: ""


                    val file = File(imagePath)

                    val thumbnail: Bitmap? = ThumbnailUtils.createImageThumbnail(
                        imagePath,
                        MediaStore.Images.Thumbnails.MINI_KIND
                    )


                    val name = file.nameWithoutExtension
                    val size = file.length() / 1024F


                    var imageDateInMillis = (cursor?.let { c ->
                        c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN))
                    } ?: "0").toLong()

                    if (imageDateInMillis == 0L || imageDateInMillis == 1656069694L) {
                        imageDateInMillis = (cursor?.let { c ->
                            c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))
                        } ?: "0").toLong()
                    }

                    if (imageDateInMillis == 0L || imageDateInMillis == 1656069694L) {
                        (cursor?.let { c ->
                            c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED))
                        } ?: "0").toLong()
                    }

                    image.name = name
                    image.path = imagePath
                    image.size = (size / 1024).roundToInt()
                    image.thumbnail = thumbnail



                    images.add(image)

                } while (cursor?.moveToNext() == true)

                cursor?.close()

                val reSelection = arrayListOf<Image>()

                for (index in images.size - 1 downTo -1 + 1) {
                    reSelection.add(images[index])
                }

                images = reSelection

            } catch (e: Exception) {
                Timber.e(e, "Failed to load images -> ${e.localizedMessage}.")
            }


            images
        }
    }


    /**
     * Gets all the images from the device and returns a list of images that are in the folder path specified
     *
     * @param folderPath The path of the folder you want to get the images from.
     * @return A list of images.
     */
    suspend fun getImagesForFolderPath(folderPath: String): List<Image> {

        return withContext(Dispatchers.IO) {

            Timber.d("folder path $folderPath")

            if (contentResolver == null) return@withContext emptyList()

            var images = arrayListOf<Image>()
            val allVideosUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.YEAR,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.DATE_MODIFIED,
            )


            val cursor = contentResolver.query(
                allVideosUri,
                projection,
                MediaStore.Images.Media.DATA + " like ? ",
                arrayOf("%$folderPath%"),
                null
            )

            try {
                cursor?.moveToFirst()
                do {

                    val image = Image()

                    val imagePath = cursor?.let { c ->
                        c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    } ?: ""


                    val file = File(imagePath)

                    val name = file.nameWithoutExtension
                    val size = file.length() / 1024F

                    val thumbnail: Bitmap? = ThumbnailUtils.createImageThumbnail(
                        imagePath,
                        MediaStore.Images.Thumbnails.MINI_KIND
                    )

                    var imageDateInMillis = (cursor?.let { c ->
                        c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN))
                    } ?: "0").toLong()

                    if (imageDateInMillis == 0L || imageDateInMillis == 1656069694L) {
                        imageDateInMillis = (cursor?.let { c ->
                            c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))
                        } ?: "0").toLong()
                    }

                    if (imageDateInMillis == 0L || imageDateInMillis == 1656069694L) {
                        imageDateInMillis = (cursor?.let { c ->
                            c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED))
                        } ?: "0").toLong()
                    }

                    image.name = name
                    image.path = imagePath
                    image.size = size.roundToInt()
                    image.thumbnail = thumbnail

                    images.add(image)

                } while (cursor?.moveToNext() == true)

                cursor?.close()

                val reSelection = arrayListOf<Image>()

                for (index in images.size - 1 downTo -1 + 1) {
                    reSelection.add(images[index])
                }

                images = reSelection

            } catch (e: Exception) {
                Timber.e(e, "Failed to load images -> ${e.localizedMessage}.")
            }

            Timber.d("images -> $images")

            images
        }
    }

}