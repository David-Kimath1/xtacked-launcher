package com.xtacked.launcher.data.model

/**
 * Unified content entry for both movies and series.
 * Poster/backdrop URLs point to remote artwork; Coil caches them for offline reuse.
 */
data class ContentItem(
    val id: String,
    val title: String,
    val type: ContentType,
    val year: Int,
    val genres: List<String>,
    val rating: Double,
    val posterUrl: String,
    val backdropUrl: String,
    val synopsis: String,
    val releaseDate: String,
    val runtimeMinutes: Int? = null,
    val seasons: Int? = null,
    val episodes: Int? = null,
    val viewingOptions: List<String> = emptyList()
) {
    val primaryGenre: String get() = genres.firstOrNull().orEmpty()
    val yearText: String get() = year.toString()
    val ratingText: String get() = if (rating > 0.0) String.format("%.1f", rating) else ""
    val genreText: String get() = genres.joinToString(", ")
    val runtimeText: String get() = when {
        type == ContentType.MOVIE && runtimeMinutes != null -> "${runtimeMinutes}m"
        type == ContentType.SERIES && seasons != null -> {
            val s = "$seasons season" + if (seasons != 1) "s" else ""
            val e = episodes?.let { ", $it episodes" }.orEmpty()
            s + e
        }
        else -> ""
    }
}
