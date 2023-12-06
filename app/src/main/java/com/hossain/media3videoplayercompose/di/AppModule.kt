package com.hossain.media3videoplayercompose.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.hossain.media3videoplayercompose.data.VideoListProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    @ViewModelScoped
    fun provideVideoListProvider(): VideoListProvider {
        return VideoListProvider()
    }
    @Provides
    @ViewModelScoped
    fun provideExoPlayer(app: Application): Player {
        return ExoPlayer.Builder(app).build()
    }
}