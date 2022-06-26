package io.github.kabirnayeem99.minigallery.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.ui.allImages.AllImagesTab
import io.github.kabirnayeem99.minigallery.ui.allImages.PICTURE_LIST_TAB_ID
import io.github.kabirnayeem99.minigallery.ui.common.PageTransitionAnimation
import io.github.kabirnayeem99.minigallery.ui.common.TopAppBarWithNavigation
import io.github.kabirnayeem99.minigallery.ui.folders.FOLDER_LIST_TAB_ID
import io.github.kabirnayeem99.minigallery.ui.folders.FolderListTab
import io.github.kabirnayeem99.minigallery.ui.folders.FolderMenuItemAction
import io.github.kabirnayeem99.minigallery.ui.folders.MenuItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@RootNavGraph(true)
@com.ramcosta.composedestinations.annotation.Destination(style = PageTransitionAnimation::class)
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {

    val tabs = HomeScreenTab.values()

    val menuItems = listOf(
        MenuItem(
            id = FolderMenuItemAction.SELECT_ALL,
            title = stringResource(id = R.string.menu_title_select_all),
            leadingIcon = Icons.Outlined.SelectAll,
        ),

        MenuItem(
            id = FolderMenuItemAction.SETTINGS,
            title = stringResource(id = R.string.menu_title_settings),
            leadingIcon = Icons.Outlined.Settings,
        ),
        MenuItem(
            id = FolderMenuItemAction.BIN,
            title = stringResource(id = R.string.menu_title_bin),
            leadingIcon = Icons.Outlined.Delete,
        ),
        MenuItem(
            id = FolderMenuItemAction.FAVOURITES,
            title = stringResource(id = R.string.menu_title_favourite),
            leadingIcon = Icons.Outlined.FavoriteBorder,
        ),
    )

    val pagerState = rememberPagerState()

    val topBar: @Composable () -> Unit = {
        TopAppBarWithNavigation(
            menuIcon = Icons.Outlined.MoreVert,
            menuIconContentDescriptor = stringResource(id = R.string.content_desc_info),
            title = {
                Row(
                    modifier = Modifier
                        .padding(22.dp)
                        .fillMaxWidth(0.8F),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = stringResource(
                            id = R.string.app_name
                        ),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.app_name
                        ),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .padding(horizontal = 12.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            },
            menuItems = menuItems,
            menuIconClickListener = { menuItem -> onMenuItemClick(menuItem) }
        )
    }

    val bottomBar: @Composable () -> Unit = { BottomBar(pagerState = pagerState) }


    Scaffold(
        topBar = { topBar() }, bottomBar = { bottomBar() },
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(bottom = it.calculateBottomPadding()))
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = it.calculateTopPadding()),
            state = pagerState,
            userScrollEnabled = false,
            count = tabs.size,
        ) {
            when (tabs[currentPage].id) {
                FOLDER_LIST_TAB_ID -> FolderListTab(navigator = navigator)
                PICTURE_LIST_TAB_ID -> AllImagesTab(navigator = navigator)
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomBar(pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    NavigationBar {
        HomeScreenTab.values().forEachIndexed { index, destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = stringResource(id = destination.label)
                    )
                },
                label = { Text(stringResource(destination.label)) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                }
            )
        }
    }
}


private fun onMenuItemClick(item: MenuItem) {
    when (item.id) {
        FolderMenuItemAction.SELECT_ALL -> {}
        FolderMenuItemAction.SETTINGS -> {}
        FolderMenuItemAction.BIN -> {}
        FolderMenuItemAction.FAVOURITES -> {}
    }
}