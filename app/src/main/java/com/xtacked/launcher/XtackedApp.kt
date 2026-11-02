package com.xtacked.launcher

import android.app.Application

/**
 * Xtacked Launcher - application entry point.
 * Exposes a single [AppContainer] for repositories and services,
 * and installs the shared Coil image loader.
 */
class XtackedApp : Application(), XtackedImageLoaderFactoryHolder {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        container = AppContainer(this)
    }

    override val imageLoaderFactory: XtackedImageLoaderFactory
        get() = XtackedImageLoaderFactory(this)

    companion object {
        lateinit var instance: XtackedApp
            private set
    }
}
