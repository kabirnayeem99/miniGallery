package io.github.kabirnayeem99.minigallery.data.dto.local.typeConverter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.TypeConverter
import timber.log.Timber
import java.io.ByteArrayOutputStream


object ImageBitmapToStringAndStringToImageBitmap {

    @TypeConverter
    fun bitmapToString(bitmap: Bitmap): String {
        return try {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
            val b: ByteArray = outputStream.toByteArray()
            Base64.encodeToString(b, Base64.DEFAULT)
        } catch (e: Exception) {
            Timber.e(e, "Failed to convert bitmap to string -> ${e.message}")
            ""
        }
    }

    @TypeConverter
    fun stringToBitmap(string: String): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(string, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            bitmap
        } catch (e: Exception) {
            Timber.e(e, "Failed to convert string back to bitmap -> ${e.message}")
            null
        }
    }
}