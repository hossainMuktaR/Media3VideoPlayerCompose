package com.hossain.media3videoplayercompose.presentation.video_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hossain.media3videoplayercompose.Common.Constant
import com.hossain.media3videoplayercompose.presentation.Screen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun VideoListScreen(
    navController: NavController,
    vm: VideoListViewModel = hiltViewModel()
) {
    val videoList = vm.videoList
    if(videoList.isEmpty()){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Video List Is Empty")
        }
    }else{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            items(videoList) { videoItem ->
                VideoItemView(
                    item = videoItem,
                    onClick = { uri ->
                        navController.navigate(
                            Screen.PlayerScreen.route +
                            "?${Constant.VIDEOURIKEY}=$uri"
                        )
                    }
                )
                Divider()
            }
        }
    }
}