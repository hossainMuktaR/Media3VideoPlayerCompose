package com.hossain.media3videoplayercompose.presentation.video_list

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.hossain.media3videoplayercompose.data.VideoItem

@Composable
fun VideoItemView(
    item: VideoItem,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                onClick(item.contentUri.toString())
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .aspectRatio(16f/9f)
                .border(2.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(10))
                .clip(RoundedCornerShape(10))
                .weight(1f),
            contentScale = ContentScale.Crop,
            bitmap = item.thumbnail.asImageBitmap(),
            contentDescription = null,

            )
        Spacer(modifier = Modifier.width(18.dp))
        Text(text = item.name, maxLines = 2, modifier = Modifier.weight(3f))
    }
}