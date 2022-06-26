package io.github.kabirnayeem99.minigallery.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.kabirnayeem99.minigallery.data.dataSource.local.CachingDataSource
import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.data.dataSource.local.db.MinGalleryCachingDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    fun provideMediaDataSource(@ApplicationContext context: Context): MediaDataSource {
        return MediaDataSource(context)
    }

    @Provides
    @Singleton
    fun provideMinGalleryCachingDatabase(
        @ApplicationContext context: Context
    ): MinGalleryCachingDatabase {
        return Room.databaseBuilder(
            context,
            MinGalleryCachingDatabase::class.java, "min_gallery_caching_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCachingDataSource(db: MinGalleryCachingDatabase): CachingDataSource {
        return CachingDataSource(db)
    }
}