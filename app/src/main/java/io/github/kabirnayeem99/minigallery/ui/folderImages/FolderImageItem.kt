package io.github.kabirnayeem99.minigallery.ui.folderImages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.kabirnayeem99.minigallery.domain.entity.Image

@Composable
fun FolderImageItem(
    modifier: Modifier = Modifier, image: Image,
    onImageClick: () -> Unit = {}
) {
    GlideImage(
        modifier = modifier
            .height(180.dp)
            .width(180.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onImageClick() },
        imageModel = image.thumbnail,
        contentScale = ContentScale.Crop,
    )
}