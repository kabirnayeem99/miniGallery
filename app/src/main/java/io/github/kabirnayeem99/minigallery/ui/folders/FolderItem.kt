package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder

@Composable
fun FolderItem(folder: ImageFolder = ImageFolder(), onFolderClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 8.dp),
        contentAlignment = Alignment.Center
    ) {

        Column {

            GlideImage(
                imageModel = folder.thumbnailPath,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onFolderClick() }
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = folder.folderName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            )
            Text(
                text = "${folder.numberOfPictures} items",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F),
                )
            )

        }
    }
}

@Composable
private fun FolderName(modifier: Modifier = Modifier, folderName: String) {
    Box(
        modifier = modifier
            .padding(bottom = 12.dp, start = 8.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 2.dp)
                .align(Alignment.Center),
            text = folderName,
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Start,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,

            )
    }
}

@Composable
private fun FolderItemCounter(modifier: Modifier = Modifier, numberOfPictures: String) {
    Box(
        modifier = modifier
            .padding(top = 12.dp, end = 8.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.tertiaryContainer),
    ) {
        Text(
            modifier = Modifier
                .height(22.dp)
                .width(22.dp)
                .align(Alignment.Center),
            text = numberOfPictures,
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                textAlign = TextAlign.Center,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,

            )
    }
}