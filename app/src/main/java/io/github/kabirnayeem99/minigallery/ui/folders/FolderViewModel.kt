package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FolderViewModel : ViewModel() {

    var uiState by mutableStateOf(FolderUiState())
        private set


    private var fetchFolderListJob: Job? = null
    fun fetchFolderList() {
        fetchFolderListJob?.cancel()
        fetchFolderListJob = viewModelScope.launch {
            delay(1000)
            uiState = uiState.copy(folderList = listOf("fasdf", "fasdf"))
        }
    }

}