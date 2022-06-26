package io.github.kabirnayeem99.minigallery.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.kabirnayeem99.minigallery.R
import io.github.kabirnayeem99.minigallery.ui.folders.MenuItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithNavigation(
    titleText: String? = null,
    title: (@Composable () -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescriptor: String? = null,
    navigationIconClickListener: () -> Unit = {},
    menuIcon: ImageVector? = null,
    menuIconContentDescriptor: String? = null,
    menuIconClickListener: (MenuItem) -> Unit = {},
    menuItems: List<MenuItem> = emptyList(),
) {

    var shouldDropDownBeEnabled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    CenterAlignedTopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 12.dp),

        title = {
            if (titleText != null && title == null) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,

                    )
            }

            if (titleText == null && title != null) title()
            else Box(modifier = Modifier)
        },
        navigationIcon = {

            if (navigationIcon != null)
                Icon(
                    imageVector = navigationIcon,
                    navigationIconContentDescriptor,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable { navigationIconClickListener() },
                )
            else Box(modifier = Modifier)
        },
        actions = {
            if (menuIcon != null) {
                Icon(
                    imageVector = menuIcon,
                    menuIconContentDescriptor,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable {
                            scope.launch {
                                shouldDropDownBeEnabled = true
                            }
                        },
                )

                DropdownMenu(
                    expanded = shouldDropDownBeEnabled,
                    onDismissRequest = { shouldDropDownBeEnabled = false }
                ) {

                    menuItems.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.title) },
                            onClick = {
                                scope.launch {
                                    shouldDropDownBeEnabled = false
                                    menuIconClickListener(item)
                                }
                            },
                            leadingIcon = {
                                item.leadingIcon?.let { icon ->
                                    Icon(
                                        icon,
                                        contentDescription = item.title
                                    )
                                }
                            },
                        )
                    }


                    MenuDefaults.Divider()

                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.menu_title_send_feedback)) },
                        onClick = { shouldDropDownBeEnabled = false },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Email,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            Icon(
                                Icons.Outlined.ArrowForward,
                                contentDescription = null
                            )
                        })
                }
            } else Box(modifier = Modifier)
        },
    )
}