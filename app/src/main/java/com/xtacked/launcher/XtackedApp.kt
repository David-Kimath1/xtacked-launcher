package com.xtacked.launcher

import android.app.Application

/**
 * Xtacked Launcher - application entry point.
 * Exposes a single [AppContainer] for repositories.
 */
class XtackedApp : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        container = AppContainer(this)
    }

    companion object {
        lateinit var instance: XtackedApp
            private set
    }
}
