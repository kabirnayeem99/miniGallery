package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.minigallery.domain.entity.Resource
import io.github.kabirnayeem99.minigallery.domain.useCase.GetFolderWithImagesListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(
    private val getFolderWithImagesListUseCase: GetFolderWithImagesListUseCase
) : ViewModel() {

    var uiState by mutableStateOf(FolderUiState())
        private set

    init {
        fetchFolderList()
    }

    private var fetchFolderListJob: Job? = null

    /**
     * Fetching all the folders from the device that contains image
     */
    private fun fetchFolderList() {
        fetchFolderListJob?.cancel()
        fetchFolderListJob = viewModelScope.launch {
            toggleLoading(true)
            val folderListResFlow = getFolderWithImagesListUseCase()

            folderListResFlow
                .distinctUntilChanged()
                .collect { folderListRes ->
                    when (folderListRes) {
                        is Resource.Success -> {

                            uiState = uiState.copy(folderList = folderListRes.data ?: emptyList())
                            toggleLoading(false)
                        }
                        is Resource.Error -> {
                            Timber.e("Got an error. For now do nothing.")
                            toggleLoading(false)
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