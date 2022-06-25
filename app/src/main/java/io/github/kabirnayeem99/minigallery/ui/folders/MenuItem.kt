package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.ui.graphics.vector.ImageVector


data class MenuItem(
    val id: MenuItemAction,
    val title: String,
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
)

interface MenuItemAction

enum class FolderMenuItemAction : MenuItemAction {
    SELECT_ALL,
    SETTINGS,
    BIN,
    FAVOURITES,
}

enum class SortMenuItemAction : MenuItemAction {
    RECENT,
    NAME_A_Z,
    NAME_Z_A,
    MODIFIED,
    SIZE,
}