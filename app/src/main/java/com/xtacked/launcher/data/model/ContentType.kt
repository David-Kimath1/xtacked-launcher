package com.xtacked.launcher.data.model

enum class ContentType {
    MOVIE,
    SERIES;

    fun label(): String = when (this) {
        MOVIE -> "Movie"
        SERIES -> "Series"
    }
}
