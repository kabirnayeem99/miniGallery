package io.github.kabirnayeem99.minigallery.domain.useCase

import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.entity.Resource
import io.github.kabirnayeem99.minigallery.domain.repositories.AllImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetAllImagesOnThisDevice @Inject constructor(private val repository: AllImageRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Image>>> {
        return repository.getAllImages().map { imageList ->
            if (imageList.isEmpty()) Resource.Error("Failed to load the images.")
            else Resource.Success(imageList)
        }.onStart {
            emit(Resource.Loading())
        }
    }
}