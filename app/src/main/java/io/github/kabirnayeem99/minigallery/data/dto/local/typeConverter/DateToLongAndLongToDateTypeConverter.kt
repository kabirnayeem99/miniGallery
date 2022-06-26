package io.github.kabirnayeem99.minigallery.data.dto.local.typeConverter

import androidx.room.TypeConverter
import timber.log.Timber
import java.util.*

object DateToLongAndLongToDateTypeConverter {
    @TypeConverter
    fun dateToLong(date: Date): Long {
        return try {
            date.time
        } catch (e: Exception) {
            Timber.e(e)
            Date().time
        }
    }

    @TypeConverter
    fun longToDate(long: Long): Date {
        return try {
            Date(long)
        } catch (e: Exception) {
            Timber.e(e)
            Date()
        }
    }
}