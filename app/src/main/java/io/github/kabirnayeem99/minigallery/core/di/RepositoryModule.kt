package io.github.kabirnayeem99.minigallery.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kabirnayeem99.minigallery.data.dataSource.local.MediaDataSource
import io.github.kabirnayeem99.minigallery.data.repositories.FolderImageRepositoryImpl
import io.github.kabirnayeem99.minigallery.data.repositories.FolderRepositoryImpl
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderImageRepository
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFolderRepository(dataSource: MediaDataSource): FolderRepository {
        return FolderRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideFolderImageRepository(dataSource: MediaDataSource): FolderImageRepository {
        return FolderImageRepositoryImpl(dataSource)
    }
}