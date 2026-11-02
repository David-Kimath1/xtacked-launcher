package com.xtacked.launcher.data.repository

import com.xtacked.launcher.data.local.UserPreferences
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.data.model.HomeFeed
import com.xtacked.launcher.data.remote.ContentApi
import com.xtacked.launcher.data.remote.MockCatalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Content catalog access with in-memory caching.
 * Falls back to the local mock catalog if the remote source ever fails,
 * so recommendations are always available offline.
 */
class ContentRepository(
    private val api: ContentApi,
    private val userPreferences: UserPreferences
) {
    private val cachedFeed = MutableStateFlow<HomeFeed?>(null)
    val feed: kotlinx.coroutines.flow.StateFlow<HomeFeed?> = cachedFeed.asStateFlow()

    suspend fun getHomeFeed(refresh: Boolean = false): HomeFeed {
        val existing = cachedFeed.value
        if (!refresh && existing != null) return existing

        val feed = runCatching { api.fetchHomeFeed() }
            .getOrElse { MockCatalog.homeFeed() }

        cachedFeed.value = feed
        runCatching { userPreferences.setLastCacheRefresh(System.currentTimeMillis()) }
        return feed
    }

    suspend fun getContent(id: String): ContentItem? {
        val fromApi = runCatching { api.fetchDetails(id) }.getOrNull()
        return fromApi ?: MockCatalog.findById(id)
    }
}
