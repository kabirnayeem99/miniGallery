package io.github.kabirnayeem99.minigallery.ui.folders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder

@Composable
fun FolderItem(folder: ImageFolder, onFolderClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(180.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onFolderClick() },
        contentAlignment = Alignment.Center
    ) {

        GlideImage(
            imageModel = folder.thumbnailPath,
            contentScale = ContentScale.Crop,
        )

        FolderItemCounter(
            modifier = Modifier.align(Alignment.TopEnd),
            numberOfPictures = folder.numberOfPictures.toString(),
        )

        FolderName(
            modifier = Modifier.Companion.align(Alignment.BottomStart),
            folderName = folder.folderName
        )
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