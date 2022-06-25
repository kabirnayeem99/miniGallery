package io.github.kabirnayeem99.minigallery.data.repositories

import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.repositories.AllImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AllImageRepositoryImpl(private val dataSource: MediaDataSource) : AllImageRepository {

    override suspend fun getAllImages(): Flow<List<Image>> {
        return flow {

            val imageList = listOf(
                Image(
                    name = "IMG_20220624_123939_1.jpg",
                    path = "/storage/emulated/0/Pictures/IMG_20220624_123939_1.jpg",
                    size = 2,
                    uri = "",
                    isSelected = false
                ),
                Image(
                    name = "IMG_20220624_123939_1.jpg",
                    path = "/storage/emulated/0/Pictures/IMG_20220624_123939_1.jpg",
                    size = 2,
                    uri = "",
                    isSelected = false
                ),
                Image(
                    name = "IMG_20220624_123939_1.jpg",
                    path = "/storage/emulated/0/Pictures/IMG_20220624_123939_1.jpg",
                    size = 2,
                    uri = "",
                    isSelected = false
                ),
                Image(
                    name = "IMG_20220624_123939_1.jpg",
                    path = "/storage/emulated/0/Pictures/IMG_20220624_123939_1.jpg",
                    size = 2,
                    uri = "",
                    isSelected = false
                ),
                Image(
                    name = "IMG_20220624_123939_1.jpg",
                    path = "/storage/emulated/0/Pictures/IMG_20220624_123939_1.jpg",
                    size = 2,
                    uri = "",
                    isSelected = false
                ),
            )
            emit(imageList)
        }
    }
}