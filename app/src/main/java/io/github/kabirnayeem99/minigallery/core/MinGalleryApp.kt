package io.github.kabirnayeem99.minigallery.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant

@HiltAndroidApp
class MinGalleryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    /**
     * If the app is in debug mode, plants a debug tree
     */
    private fun setUpTimber() {
        plant(Timber.DebugTree())
    }
}