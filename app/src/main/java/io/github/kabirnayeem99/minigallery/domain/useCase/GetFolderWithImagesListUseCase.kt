package io.github.kabirnayeem99.minigallery.domain.useCase

import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import io.github.kabirnayeem99.minigallery.domain.entity.Resource
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


/**
 * Gets all the folders with images.
 */
class GetFolderWithImagesListUseCase @Inject constructor(
    private val repository: FolderRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<ImageFolder>>> {
        return repository.provideFolderList().map { imageFolderList ->
            if (imageFolderList.isEmpty()) Resource.Error("No folders found on your device.")
            else Resource.Success(imageFolderList)
        }.onStart {
            emit(Resource.Loading())
        }
    }
}