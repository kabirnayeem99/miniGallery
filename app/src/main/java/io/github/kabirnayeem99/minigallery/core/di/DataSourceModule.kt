package io.github.kabirnayeem99.minigallery.core.di

import android.content.ContentResolver
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    fun provideMediaDataSource(@ApplicationContext context: Context): MediaDataSource {
        return MediaDataSource(context)
    }
}