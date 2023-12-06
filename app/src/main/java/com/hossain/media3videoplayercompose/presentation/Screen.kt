package com.hossain.media3videoplayercompose.presentation

sealed class Screen(val route: String) {
    object VideoListScreen: Screen("video_list_screen")
    object PlayerScreen: Screen("player_screen")
}