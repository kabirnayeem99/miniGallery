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

    fun fetchImagesByFolder(folderPath: String) {
        fetchImagesJob?.cancel()
        fetchImagesJob = viewModelScope.launch {
            val imagesFlowRes = getFolderImagesUseCase(folderPath)

            imagesFlowRes.collect { res ->
                Timber.d(res.toString())
                when (res) {
                    is Resource.Success -> {
                        uiState = uiState.copy(imageList = res.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        Timber.e("Got error -> ${res.message}")
                    }
                    else -> Unit
                }
            }
        }
    }

}