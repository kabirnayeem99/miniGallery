package io.github.kabirnayeem99.minigallery.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kabirnayeem99.minigallery.data.dataSource.local.CachingDataSource
import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.data.repositories.AllImageRepositoryImpl
import io.github.kabirnayeem99.minigallery.data.repositories.FolderImageRepositoryImpl
import io.github.kabirnayeem99.minigallery.data.repositories.FolderRepositoryImpl
import io.github.kabirnayeem99.minigallery.domain.repositories.AllImageRepository
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderImageRepository
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFolderRepository(dataSource: MediaDataSource, cachingDataSource: CachingDataSource): FolderRepository {
        return FolderRepositoryImpl(dataSource, cachingDataSource)
    }

    @Singleton
    @Provides
    fun provideFolderImageRepository(dataSource: MediaDataSource): FolderImageRepository {
        return FolderImageRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideAllImageRepository(dataSource: MediaDataSource, cachingDataSource: CachingDataSource): AllImageRepository {
        return AllImageRepositoryImpl(dataSource, cachingDataSource)
    }
}