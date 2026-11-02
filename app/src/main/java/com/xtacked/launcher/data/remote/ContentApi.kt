package com.xtacked.launcher.data.remote

import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.data.model.HomeFeed

/**
 * Abstraction over the content source. Today it is backed by a local mock catalog.
 * A future legitimate metadata API implementation can be dropped in without
 * touching the UI or repositories.
 */
interface ContentApi {
    suspend fun fetchHomeFeed(): HomeFeed
    suspend fun fetchDetails(id: String): ContentItem?
}
