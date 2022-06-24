package io.github.kabirnayeem99.minigallery.ui.folderImages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.minigallery.domain.entity.Resource
import io.github.kabirnayeem99.minigallery.domain.useCase.GetFolderImagesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FolderImageListViewModel @Inject constructor(
    private val getFolderImagesUseCase: GetFolderImagesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(FolderImageUiState())
        private set

    private var fetchImagesJob: Job? = null

    /**
     * Fetches images from a folder and updates the UI state with the result
     *
     * @param folderPath The path of the folder to fetch images from.
     */
    fun fetchImagesByFolder(folderPath: String) {
        fetchImagesJob?.cancel()
        fetchImagesJob = viewModelScope.launch {
            val imagesFlowRes = getFolderImagesUseCase(folderPath)

            imagesFlowRes.collect { res ->
                Timber.d(res.toString())
                when (res) {
                    is Resource.Success -> {
                        uiState = uiState.copy(imageList = res.data ?: emptyList())
                        toggleLoading(false)
                    }
                    is Resource.Error -> {
                        toggleLoading(false)
                        Timber.e("Got error -> ${res.message}")
                    }
                    is Resource.Loading -> {
                        toggleLoading(true)
                    }
                }
            }
        }
    }

    /**
     * Toggles loading indicator on or off
     *
     * @param shouldTurnOn Boolean - This is a boolean value that determines whether the loading
     * indicator should be shown or not.
     */
    private fun toggleLoading(shouldTurnOn: Boolean) {
        uiState = uiState.copy(isLoading = shouldTurnOn)
    }

}