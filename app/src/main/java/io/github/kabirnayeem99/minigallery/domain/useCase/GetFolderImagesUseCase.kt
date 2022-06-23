package io.github.kabirnayeem99.minigallery.domain.useCase

import io.github.kabirnayeem99.minigallery.domain.entity.Image
import io.github.kabirnayeem99.minigallery.domain.entity.Resource
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFolderImagesUseCase @Inject constructor(private val repository: FolderImageRepository) {
    suspend operator fun invoke(folderPath: String): Flow<Resource<List<Image>>> {
        return repository.getAllImageByPath(folderPath).map { imageList ->
            if (imageList.isEmpty()) Resource.Error("Failed to load the images.")
            else Resource.Success(imageList)
        }
    }
}