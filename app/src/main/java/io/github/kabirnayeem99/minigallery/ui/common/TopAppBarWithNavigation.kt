package io.github.kabirnayeem99.minigallery.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TopAppBarWithNavigation(
    startIcon: ImageVector,
    startIconContentDescriptor: String,
    endIcon: ImageVector,
    endIconContentDescriptor: String,
    titleText: String,
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = startIcon,
                startIconContentDescriptor,
                modifier = Modifier.clickable { startIconClickListener() },
            )
            Text(
                text = titleText,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                )
            )
            Icon(
                imageVector = endIcon, endIconContentDescriptor,
                modifier = Modifier.clickable { endIconClickListener() },

                )
        }

    }
}