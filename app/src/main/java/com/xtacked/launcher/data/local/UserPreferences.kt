package com.xtacked.launcher.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.xtackedDataStore: DataStore<Preferences> by
    preferencesDataStore(name = "xtacked_prefs")

/**
 * Lightweight app preferences: last successful catalog cache time, offline-first flag.
 * The content feed itself comes from a local catalog so recommendations always work offline.
 */
class UserPreferences(private val context: Context) {

    val lastCacheRefresh: Flow<Long> =
        context.xtackedDataStore.data.map { it[KEY_LAST_REFRESH] ?: 0L }

    val preferOnline: Flow<Boolean> =
        context.xtackedDataStore.data.map { it[KEY_PREFER_ONLINE] ?: true }

    suspend fun setLastCacheRefresh(timestamp: Long) {
        context.xtackedDataStore.edit { it[KEY_LAST_REFRESH] = timestamp }
    }

    suspend fun setPreferOnline(value: Boolean) {
        context.xtackedDataStore.edit { it[KEY_PREFER_ONLINE] = value }
    }

    private companion object {
        val KEY_LAST_REFRESH = longPreferencesKey("last_cache_refresh")
        val KEY_PREFER_ONLINE = booleanPreferencesKey("prefer_online")
    }
}
