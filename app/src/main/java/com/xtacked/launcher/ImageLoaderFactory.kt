package com.xtacked.launcher

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache

/**
 * Shared image loader for Xtacked Launcher.
 * - 20% of RAM memory cache (respectful on low-end TV boxes)
 * - 256 MB disk cache so posters/backdrops persist across restarts
 * - Crossfade for smooth, lightweight transitions
 */
fun buildXtackedImageLoader(context: Context): ImageLoader =
    ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.20)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("xtacked_image_cache"))
                .maxSizeBytes(256L * 1024L * 1024L)
                .build()
        }
        .crossfade(180)
        .respectCacheHeaders(false)
        .build()

class XtackedImageLoaderFactory(private val context: Context) : ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader = buildXtackedImageLoader(context)
}
