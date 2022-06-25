package io.github.kabirnayeem99.minigallery.ui.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Image
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.ui.allImages.PICTURE_LIST_TAB_ID
import io.github.kabirnayeem99.minigallery.ui.folders.FOLDER_LIST_TAB_ID


enum class HomeScreenTab(
    val id: String = "",
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Pictures(PICTURE_LIST_TAB_ID, Icons.Outlined.Image, R.string.label_pictures),
    Folders(FOLDER_LIST_TAB_ID, Icons.Outlined.Folder, R.string.label_folders)
}