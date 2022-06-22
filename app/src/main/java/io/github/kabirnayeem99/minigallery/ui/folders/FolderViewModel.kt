package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.minigallery.data.dataSource.development.RandomDataProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(FolderUiState())
        private set


    private var fetchFolderListJob: Job? = null

    /**
     * Fetching all the folders from the device that contains image
     */
    fun fetchFolderList() {
        fetchFolderListJob?.cancel()
        fetchFolderListJob = viewModelScope.launch {
            toggleLoading(true)
            delay(600)
            uiState = uiState.copy(folderList = RandomDataProvider.provideRandomImageFolders())
            toggleLoading(false)
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