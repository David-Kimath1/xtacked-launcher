package com.xtacked.launcher.data.remote

import com.xtacked.launcher.data.model.ContentCarousel
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.data.model.ContentType
import com.xtacked.launcher.data.model.HomeFeed

/**
 * Local mock catalog. No network required to build the feed.
 * Artwork URLs are remote; Coil caches them so previously viewed posters remain
 * available offline. All content is fictional to avoid implying access to paid services.
 */
object MockCatalog {

    private fun poster(seed: String) = "https://picsum.photos/seed/$seed/500/750"
    private fun backdrop(seed: String) = "https://picsum.photos/seed/$seed-b/1280/720"
    private val GENERIC_VIEWING_OPTIONS = listOf("Stream", "Rent")

    private fun movie(
        id: String,
        title: String,
        year: Int,
        genres: List<String>,
        rating: Double,
        runtime: Int,
        synopsis: String
    ) = ContentItem(
        id = id,
        title = title,
        type = ContentType.MOVIE,
        year = year,
        genres = genres,
        rating = rating,
        posterUrl = poster(id),
        backdropUrl = backdrop(id),
        synopsis = synopsis,
        releaseDate = "$year-03-15",
        runtimeMinutes = runtime,
        viewingOptions = GENERIC_VIEWING_OPTIONS
    )

    private fun series(
        id: String,
        title: String,
        year: Int,
        genres: List<String>,
        rating: Double,
        seasons: Int,
        episodes: Int,
        synopsis: String
    ) = ContentItem(
        id = id,
        title = title,
        type = ContentType.SERIES,
        year = year,
        genres = genres,
        rating = rating,
        posterUrl = poster(id),
        backdropUrl = backdrop(id),
        synopsis = synopsis,
        releaseDate = "$year-09-01",
        seasons = seasons,
        episodes = episodes,
        viewingOptions = GENERIC_VIEWING_OPTIONS
    )

    val movies: List<ContentItem> = listOf(
        movie("m-001", "Obsidian Horizon", 2023, listOf("Sci-Fi", "Thriller"), 8.1, 128,
            "A deep-space salvage crew uncovers a derelict vessel that remembers them."),
        movie("m-002", "The Last Signal", 2022, listOf("Mystery", "Drama"), 7.6, 112,
            "A radio astronomer hears a voice that should not exist."),
        movie("m-003", "Neon Harvest", 2024, listOf("Action", "Sci-Fi"), 7.9, 134,
            "In a flooded megacity, a courier must outrun the corporation she once served."),
        movie("m-004", "Crimson Meridian", 2021, listOf("Thriller", "Crime"), 7.2, 105,
            "A detective chasing a phantom killer discovers the map is the motive."),
        movie("m-005", "Echoes of Dust", 2020, listOf("Drama", "Adventure"), 8.4, 146,
            "Two siblings cross a dying continent to honour a promise."),
        movie("m-006", "Shatter Line", 2023, listOf("Action", "Thriller"), 6.8, 118,
            "A demolitions expert is blackmailed into one final job."),
        movie("m-007", "Glass Requiem", 2022, listOf("Horror", "Mystery"), 7.0, 96,
            "A cathedral choir begins to sing a hymn no one taught them."),
        movie("m-008", "Northern Halo", 2024, listOf("Drama", "Romance"), 7.7, 124,
            "Under the aurora, two strangers rewrite each other's futures."),
        movie("m-009", "Static Sky", 2019, listOf("Sci-Fi"), 7.5, 130,
            "When the atmosphere begins to hum, one pilot hears the tune first."),
        movie("m-010", "Tidewater", 2023, listOf("Adventure", "Family"), 7.1, 99,
            "A coastal town's children discover the sea remembers their grandparents."),
        movie("m-011", "Fractured Crown", 2022, listOf("Fantasy", "Adventure"), 8.0, 152,
            "An exiled queen returns to a kingdom that no longer recognises her name."),
        movie("m-012", "Silent Frequency", 2024, listOf("Mystery", "Thriller"), 7.4, 108,
            "A sound engineer finds a conversation hidden inside a dead broadcast."),
        movie("m-013", "Iron Betrayal", 2021, listOf("Action", "Crime"), 7.3, 121,
            "A retired enforcer is dragged back into the family business."),
        movie("m-014", "Emberlight", 2020, listOf("Drama", "Fantasy"), 7.8, 138,
            "A girl trades memories for fire, and the fire starts asking for more."),
        movie("m-015", "Dead Reckoning", 2023, listOf("Action", "War"), 6.9, 127,
            "An ace navigator is the only one who can bring the squadron home."),
        movie("m-016", "Paper Suns", 2022, listOf("Drama"), 8.2, 115,
            "Three generations of farmers face the last harvest of their lives."),
        movie("m-017", "The Hollow Tide", 2024, listOf("Horror", "Thriller"), 6.6, 103,
            "At low tide, the village learns what lives under the sand."),
        movie("m-018", "Blue Vector", 2021, listOf("Sci-Fi", "Action"), 7.5, 132,
            "A test pilot becomes the first person to outrun her own reflection."),
        movie("m-019", "Vanishing Point Zero", 2023, listOf("Mystery", "Sci-Fi"), 8.3, 141,
            "Every road leads to a town the maps refuse to show."),
        movie("m-020", "Cold Ember", 2020, listOf("Drama", "Crime"), 7.7, 118,
            "A firefighter's past is found in the ashes of a stranger's house.")
    )

    val series: List<ContentItem> = listOf(
        series("s-001", "Fractured Sky", 2022, listOf("Sci-Fi", "Drama"), 8.6, 3, 24,
            "Colonists on a fractured moon discover their calendar lies."),
        series("s-002", "Dust and Bone", 2021, listOf("Drama", "Western"), 8.0, 2, 16,
            "A frontier marshal must choose between the law and the land."),
        series("s-003", "Neon Divide", 2023, listOf("Sci-Fi", "Thriller"), 8.4, 2, 18,
            "Two cities share one wall, one sun, and one very dangerous secret."),
        series("s-004", "The Ninth Precinct", 2020, listOf("Crime", "Drama"), 8.2, 4, 40,
            "An ensemble of detectives investigates the cases the city forgets."),
        series("s-005", "Ghostlight", 2022, listOf("Horror", "Mystery"), 7.7, 2, 16,
            "A theatre company discovers the ghost light is not for ghosts."),
        series("s-006", "Silent Harvest", 2024, listOf("Drama"), 8.1, 1, 10,
            "A rural community confronts the price of one perfect summer."),
        series("s-007", "Iron Shore", 2021, listOf("Action", "Drama"), 7.8, 3, 30,
            "A coastal rescue squad wages war on the sea and each other."),
        series("s-008", "Palace of Ash", 2023, listOf("Fantasy", "Drama"), 8.5, 2, 20,
            "The last court of a fire kingdom plots its impossible return."),
        series("s-009", "The Undercurrent", 2022, listOf("Mystery", "Thriller"), 8.0, 2, 16,
            "A coastal town hears a second tide where no tide should be."),
        series("s-010", "Hollow Town", 2020, listOf("Drama", "Mystery"), 7.9, 3, 24,
            "A town wakes up with one house and one memory missing."),
        series("s-011", "Starlight Protocol", 2024, listOf("Sci-Fi", "Action"), 8.3, 1, 8,
            "An orbital crew must disable their own defence grid before sunrise."),
        series("s-012", "Cinder and Smoke", 2021, listOf("Crime", "Drama"), 8.1, 2, 18,
            "Two families, one fire, and no alibi that holds."),
        series("s-013", "The Long Vigil", 2023, listOf("Drama", "War"), 8.4, 1, 10,
            "A lighthouse crew keeps watch over a sea that never calms."),
        series("s-014", "Paper Kingdom", 2022, listOf("Fantasy", "Adventure"), 7.6, 2, 16,
            "A cartographer discovers a kingdom that exists only on her maps."),
        series("s-015", "Fracture Lines", 2021, listOf("Thriller"), 7.4, 3, 24,
            "A negotiator's family becomes her final hostage situation."),
        series("s-016", "Beneath the Salt", 2024, listOf("Mystery", "Drama"), 8.2, 1, 8,
            "A salt-flat town digs deeper than its elders will allow."),
        series("s-017", "Echo Run", 2020, listOf("Action", "Sci-Fi"), 7.7, 4, 40,
            "A courier races across time to deliver a message to herself."),
        series("s-018", "Vermillion", 2023, listOf("Horror", "Thriller"), 7.5, 2, 14,
            "A painter's red pigment begins to redraw the city at night."),
        series("s-019", "The Quiet Ledger", 2022, listOf("Crime", "Drama"), 8.0, 2, 16,
            "A forensic accountant reads between the lines of the city's blood."),
        series("s-020", "Orbital Drift", 2024, listOf("Sci-Fi"), 8.1, 1, 10,
            "The station's orbit drifts one second a day. One second is enough.")
    )

    private val allItems: List<ContentItem> = movies + series

    fun findById(id: String): ContentItem? = allItems.firstOrNull { it.id == id }

    fun homeFeed(): HomeFeed = HomeFeed(
        carousels = listOf(
            ContentCarousel("recommended-movies", "Recommended Movies", movies.take(8)),
            ContentCarousel("popular-movies", "Popular Movies", movies.drop(6).take(8)),
            ContentCarousel("recommended-series", "Recommended Series", series.take(8)),
            ContentCarousel("popular-series", "Popular Series", series.drop(6).take(8)),
            ContentCarousel(
                "recently-added",
                "Recently Added",
                (movies.sortedByDescending { it.year }.take(6) +
                    series.sortedByDescending { it.year }.take(6)).take(10)
            ),
            ContentCarousel(
                "trending",
                "Trending Content",
                (movies.drop(2).take(6) + series.drop(2).take(6)).take(10)
            )
        )
    )
}
