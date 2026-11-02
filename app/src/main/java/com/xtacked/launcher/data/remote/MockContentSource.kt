package com.xtacked.launcher.data.remote

import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.data.model.HomeFeed

/**
 * Offline-safe ContentApi implementation. Never touches the network.
 * A future ApiContentSource can be swapped in via AppContainer without UI changes.
 */
class MockContentSource : ContentApi {
    override suspend fun fetchHomeFeed(): HomeFeed = MockCatalog.homeFeed()
    override suspend fun fetchDetails(id: String): ContentItem? = MockCatalog.findById(id)
}
