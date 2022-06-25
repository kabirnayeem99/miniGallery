package io.github.kabirnayeem99.minigallery.ui.allImages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.minigallery.data.repositories.AllImagesUiState
import io.github.kabirnayeem99.minigallery.domain.entity.Resource
import io.github.kabirnayeem99.minigallery.domain.useCase.GetAllImagesOnThisDevice
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllImagesViewModel @Inject constructor(
    private val getAllImagesOnThisDevice: GetAllImagesOnThisDevice
) : ViewModel() {
    var uiState by mutableStateOf(AllImagesUiState())
        private set

    private var fetchAllImagesJob: Job? = null
    fun fetchAllImages() {
        fetchAllImagesJob?.cancel()
        fetchAllImagesJob = viewModelScope.launch {
            getAllImagesOnThisDevice().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        uiState = uiState.copy(imagesList = resource.data ?: emptyList())
                    }
                    else -> Unit
                }
            }
        }
    }
}