package io.github.kabirnayeem99.minigallery.data.dataSource.local

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import timber.log.Timber
import javax.inject.Inject


class MediaDataSource @Inject constructor(context: Context) {

    private val contentResolver = context.contentResolver

    /**
     * Gets all the images from the device and then groups them into folders
     *
     * @return the list of folders with images.
     */
    fun getFolderWithPicturesFromTheDevice(): List<ImageFolder> {

        if (contentResolver == null) return emptyList()

        val folders = arrayListOf<ImageFolder>()
        val picturePaths = arrayListOf<String>()
        val allPicturesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID
        )

        val cursor: Cursor? = contentResolver.query(
            allPicturesUri, projection,
            null,
            null,
            null,
        )

        try {
            cursor?.moveToFirst()

            do {
                val folder = ImageFolder()

                val displayName = cursor?.let {
                    it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                } ?: ""

                val bucketName = cursor?.let {
                    it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                } ?: ""

                val dataPath = cursor?.let {
                    it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                } ?: ""

                Timber.d("$displayName, $bucketName, $dataPath")

//                var folderPaths = dataPath.replace(displayName, "")
//
//                folderPaths = if (folderPaths.isNotBlank()) "$folderPaths$bucketName/" else ""

                var folderPaths: String = dataPath.substring(
                    0, dataPath.lastIndexOf(
                        "$bucketName/"
                    )
                )
                folderPaths = "$folderPaths$bucketName/"

                Timber.d("folder paths -> $folderPaths")

                if (folderPaths.isNotBlank() && !picturePaths.contains(folderPaths)) {
                    picturePaths.add(folderPaths)
                    folder.path = folderPaths
                    folder.folderName = bucketName
                    folder.thumbnailPath = dataPath
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

        Timber.d(folders.toString())

        return folders.toList()
    }


    /**
     * Gets all the images from the device and returns a list of images that are in the folder path specified
     *
     * @param folderPath The path of the folder you want to get the images from.
     * @return A list of images.
     */
    fun getImagesForFolderPath(folderPath: String): List<Image> {

        Timber.d("folder path $folderPath")

        if (contentResolver == null) return emptyList()

        var images = arrayListOf<Image>()
        val allVideosUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
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

                val imageName = cursor?.let { c ->
                    c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                } ?: ""

                val imagePath = cursor?.let { c ->
                    c.getString(c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                } ?: ""

                val imageSize = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE) ?: 0

                image.name = imageName
                image.path = imagePath
                image.size = imageSize

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


        return images
    }

}