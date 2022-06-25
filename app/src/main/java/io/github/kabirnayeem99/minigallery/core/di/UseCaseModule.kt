package io.github.kabirnayeem99.minigallery.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.kabirnayeem99.minigallery.domain.repositories.AllImageRepository
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderImageRepository
import io.github.kabirnayeem99.minigallery.domain.repositories.FolderRepository
import io.github.kabirnayeem99.minigallery.domain.useCase.GetAllImagesOnThisDevice
import io.github.kabirnayeem99.minigallery.domain.useCase.GetFolderImagesUseCase
import io.github.kabirnayeem99.minigallery.domain.useCase.GetFolderWithImagesListUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetFolderWithImagesListUseCase(
        repository: FolderRepository
    ): GetFolderWithImagesListUseCase {
        return GetFolderWithImagesListUseCase(repository)
    }

    @Provides
    fun provideGetFolderImagesUseCase(
        repository: FolderImageRepository
    ): GetFolderImagesUseCase {
        return GetFolderImagesUseCase(repository)
    }

    @Provides
    fun provideGetAllImagesOnThisDevice(repository: AllImageRepository): GetAllImagesOnThisDevice {
        return GetAllImagesOnThisDevice(repository)
    }

}