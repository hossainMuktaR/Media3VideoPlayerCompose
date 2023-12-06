package com.hossain.media3videoplayercompose.data

import android.app.Application
import android.content.ContentUris
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import java.io.IOException


class VideoListProvider() {

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getVideoList(app: Application): MutableList<VideoItem> {
        val cursor = app.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Video.VideoColumns._ID,
                MediaStore.Video.VideoColumns.DISPLAY_NAME
            ),
            null,
            null,
            null
        )
        val videoList = mutableListOf<VideoItem>()
        try {
            when (cursor?.count) {
                null -> {
                    Log.i("mainModel", "cursor null")
                }

                0 -> {
                    Log.i("mainModel", "cursor 0")
                }

                else -> {
                    Log.i("mainModel", "cursor found")
                    val titleColumn = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
                    val uriColumn = cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)
                    while (cursor.moveToNext()) {
                        val videoUri = ContentUris.withAppendedId(
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, cursor.getLong(uriColumn)
                        )
                        var thumbnail: Bitmap? = null
                        try {
                            thumbnail = app.contentResolver.loadThumbnail(
                                videoUri, Size(640, 480), null
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        Log.i("mainModel", cursor.getString(titleColumn))
                        val video = VideoItem(
                            contentUri = videoUri,
                            name = cursor.getString(titleColumn),
                            thumbnail = thumbnail ?: continue
                        )
                        videoList.add(video)
                    }
                    return videoList
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }finally {
            cursor?.close()
        }
        return videoList
    }
}