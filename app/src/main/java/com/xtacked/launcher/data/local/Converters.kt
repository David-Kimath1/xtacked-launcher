package com.xtacked.launcher.data.local

import androidx.room.TypeConverter
import com.xtacked.launcher.data.model.ContentType

class Converters {
    @TypeConverter
    fun fromStringList(list: List<String>?): String =
        list?.joinToString(SEPARATOR).orEmpty()

    @TypeConverter
    fun toStringList(value: String): List<String> =
        if (value.isBlank()) emptyList() else value.split(SEPARATOR)

    @TypeConverter
    fun fromContentType(type: ContentType): String = type.name

    @TypeConverter
    fun toContentType(value: String): ContentType = ContentType.valueOf(value)

    private companion object {
        const val SEPARATOR = "\u001F"
    }
}
