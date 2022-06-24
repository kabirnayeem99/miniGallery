package io.github.kabirnayeem99.minigallery.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun TopAppBarWithNavigation(
    startIcon: ImageVector? = null,
    startIconContentDescriptor: String? = null,
    endIcon: ImageVector? = null,
    endIconContentDescriptor: String? = null,
    titleText: String = "",
    startIconClickListener: () -> Unit = {},
    endIconClickListener: () -> Unit = {},
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.background.copy(0.2F),
        elevation = 0.dp,
        contentPadding = PaddingValues(top = 12.dp, start = 12.dp, end = 12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (startIcon != null)
                Icon(
                    imageVector = startIcon,
                    startIconContentDescriptor,
                    modifier = Modifier.clickable { startIconClickListener() },
                )
            else Box(modifier = Modifier)
            Text(
                text = titleText,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(horizontal = 12.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            if (endIcon != null)
                Icon(
                    imageVector = endIcon,
                    endIconContentDescriptor,
                    modifier = Modifier.clickable { endIconClickListener() },
                )
            else Box(modifier = Modifier)

        }

    }
}