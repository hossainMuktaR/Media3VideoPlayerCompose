package com.hossain.media3videoplayercompose.presentation.video_list

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.hossain.media3videoplayercompose.data.VideoItem
import com.hossain.media3videoplayercompose.data.VideoListProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.Q)
@HiltViewModel
class VideoListViewModel @Inject constructor(
    val application: Application,
    val videoListProvider: VideoListProvider,
): ViewModel(){
    private var _videoList = mutableListOf<VideoItem>()
    val videoList: MutableList<VideoItem> = _videoList

    init {
        retrieveVideoList()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun retrieveVideoList() {
        _videoList.addAll(videoListProvider.getVideoList(application))
    }
}
