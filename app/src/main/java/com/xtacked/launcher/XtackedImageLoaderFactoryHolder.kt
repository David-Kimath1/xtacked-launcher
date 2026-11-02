package com.xtacked.launcher

/**
 * Marker interface so Coil's singleton loader can be provided via the Application.
 */
interface XtackedImageLoaderFactoryHolder : coil.ImageLoaderFactory {
    val imageLoaderFactory: XtackedImageLoaderFactory
    override fun newImageLoader() = imageLoaderFactory.newImageLoader()
}
