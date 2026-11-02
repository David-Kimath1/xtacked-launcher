# Xtacked Launcher - R8 keep rules

# Keep data models (Gson / reflection)
-keep class com.xtacked.launcher.data.model.** { *; }

# Keep Room generated entities and DAOs
-keep class com.xtacked.launcher.data.local.** { *; }
-keep @androidx.room.Entity class * { *; }

# Kotlin coroutines
-dontwarn kotlinx.coroutines.**

# Coil
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn coil.**

# Compose (mostly handled by AGP, but keep TV material)
-keep class androidx.tv.** { *; }
