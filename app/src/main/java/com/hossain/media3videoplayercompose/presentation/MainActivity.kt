package com.hossain.media3videoplayercompose.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hossain.media3videoplayercompose.Common.Constant
import com.hossain.media3videoplayercompose.presentation.Screen
import com.hossain.media3videoplayercompose.presentation.player_screen.PlayerScreen
import com.hossain.media3videoplayercompose.presentation.video_list.VideoListScreen
import com.hossain.media3videoplayercompose.ui.theme.Media3VideoPlayerComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Media3VideoPlayerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.VideoListScreen.route
                    ) {

                        composable(
                            route = Screen.VideoListScreen.route
                        ) {
                            VideoListScreen(navController = navController)
                        }

                        composable(
                            route = Screen.PlayerScreen.route +
                            "?${Constant.VIDEOURIKEY}={${Constant.VIDEOURIKEY}}",
                            arguments = listOf(
                                navArgument(
                                    name = Constant.VIDEOURIKEY
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            PlayerScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}
