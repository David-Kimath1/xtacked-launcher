package com.xtacked.launcher.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.data.model.ContentType

@Entity(tableName = "my_list")
data class MyListEntity(
    @PrimaryKey val id: String,
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
    val viewingOptions: List<String> = emptyList(),
    val addedAt: Long = System.currentTimeMillis()
) {
    fun toContentItem(): ContentItem = ContentItem(
        id = id,
        title = title,
        type = type,
        year = year,
        genres = genres,
        rating = rating,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        synopsis = synopsis,
        releaseDate = releaseDate,
        runtimeMinutes = runtimeMinutes,
        seasons = seasons,
        episodes = episodes,
        viewingOptions = viewingOptions
    )

    companion object {
        fun from(item: ContentItem): MyListEntity = MyListEntity(
            id = item.id,
            title = item.title,
            type = item.type,
            year = item.year,
            genres = item.genres,
            rating = item.rating,
            posterUrl = item.posterUrl,
            backdropUrl = item.backdropUrl,
            synopsis = item.synopsis,
            releaseDate = item.releaseDate,
            runtimeMinutes = item.runtimeMinutes,
            seasons = item.seasons,
            episodes = item.episodes,
            viewingOptions = item.viewingOptions
        )
    }
}
