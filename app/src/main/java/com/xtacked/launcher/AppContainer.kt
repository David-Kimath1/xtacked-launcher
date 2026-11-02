package com.xtacked.launcher

import android.content.Context
import androidx.room.Room
import com.xtacked.launcher.data.NetworkMonitor
import com.xtacked.launcher.data.local.UserPreferences
import com.xtacked.launcher.data.local.XtackedDatabase
import com.xtacked.launcher.data.remote.ContentApi
import com.xtacked.launcher.data.remote.MockContentSource
import com.xtacked.launcher.data.repository.ContentRepository
import com.xtacked.launcher.data.repository.MyListRepository

/**
 * Manual dependency container. Simple and fast for low-end TV boxes.
 * Swap [ContentApi] for a future network-backed implementation without touching UI.
 */
class AppContainer(context: Context) {

    private val database: XtackedDatabase = Room.databaseBuilder(
        context.applicationContext,
        XtackedDatabase::class.java,
        "xtacked.db"
    ).fallbackToDestructiveMigration().build()

    val userPreferences: UserPreferences = UserPreferences(context.applicationContext)

    val networkMonitor: NetworkMonitor = NetworkMonitor(context.applicationContext)

    val contentApi: ContentApi = MockContentSource()

    val contentRepository: ContentRepository =
        ContentRepository(api = contentApi, userPreferences = userPreferences)

    val myListRepository: MyListRepository =
        MyListRepository(database.myListDao())
}
