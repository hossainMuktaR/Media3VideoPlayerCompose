package com.hossain.media3videoplayercompose.data

import android.graphics.Bitmap
import android.net.Uri

data class VideoItem (
    val contentUri: Uri,
    val name: String,
    val thumbnail: Bitmap
)