
Xtacked Launcher

A premium, dark cinematic content-discovery launcher built for Android TV and remote navigation.

Highlights

Six recommendation carousels: Recommended Movies, Popular Movies, Recommended Series,
Popular Series, Recently Added, Trending Content.

Persistent My List backed by Room - survives restarts, works fully offline.

Full details page with backdrop, poster, synopsis, metadata, runtime / episode info,
add/remove My List, and viewing-option chips.

Remote-first navigation: D-pad left/right/up/down, OK to open or act, Back to return.
Every focusable element shows a clear white focus ring; cards scale slightly and glow.

Fully offline-friendly: bundled vector artwork, Coil disk cache, offline banner on home.

Package

Application ID: com.xtacked.launcher

Minimum SDK: 21

Target SDK: 34

Launcher category: LAUNCHER + LEANBACK_LAUNCHER

Architecture

data/

model/: ContentItem, ContentCarousel, HomeFeed, ContentType

local/: Room database (My List), DataStore preferences

remote/: ContentApi interface + MockContentSource + MockCatalog

repository/: ContentRepository, MyListRepository

NetworkMonitor: connectivity Flow (banner only)

ui/

theme/: Colors, typography, TV Material theme

components/: ContentCard, CarouselRow, FocusableChip, OfflineBanner

home/: HomeScreen + HomeViewModel

details/: DetailsScreen + DetailsViewModel

mylist/: MyListScreen + MyListViewModel

navigation/: Route constants

AppContainer: Manual DI

XtackedApp: Application + Coil ImageLoader

Extending to a real metadata API

ContentApi is the single swap point:

Implement ApiContentSource : ContentApi using Retrofit/OkHttp against a legitimate metadata provider (e.g. TMDB, Trakt, or your own backend).

Cache responses in Room or DataStore if you want partial-offline caching beyond Coil artwork.

In AppContainer, replace MockContentSource() with your implementation.

No UI, ViewModel, or repository changes are required.

Building

./gradlew assembleDebug        # debug APK
./gradlew assembleRelease      # R8-minified release APK (add signing config first)


Assets

All artwork is generated locally as vector drawables (poster_01.xml..poster_12.xml, backdrop_01.xml..backdrop_06.xml). No third-party images, no scraping, no streaming-service logos. Safe for public distribution.

Notes on streaming

The app does not scrape or assume access to any paid streaming service. viewingOptions on each item is free-form text ("Stream", "Rent") and can be replaced with real entitlement data if the operator has it.
