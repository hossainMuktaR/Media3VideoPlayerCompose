package com.hossain.media3videoplayercompose.presentation.player_screen

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.hossain.media3videoplayercompose.Common.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    val player: Player,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _videoUriString = mutableStateOf<String?>(null)
    val videoUriString: State<String?> = _videoUriString
     private val _uriFound = mutableStateOf<Boolean>(true)
     val uriFound: State<Boolean> = _uriFound


    init {
        player.prepare()
        _videoUriString.value = savedStateHandle.get<String>(Constant.VIDEOURIKEY)
        playMedia()
    }
    fun playMedia() {
        val uri = Uri.parse(_videoUriString.value ?: return)
        if(uri.scheme != "content") {
            _uriFound.value = false
            return
        }
        val mediaVideo = MediaItem.fromUri(uri)
        player.setMediaItem(
            mediaVideo
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
