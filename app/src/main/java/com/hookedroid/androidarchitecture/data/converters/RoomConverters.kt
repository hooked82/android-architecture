package com.hookedroid.androidarchitecture.data.converters

import androidx.room.TypeConverter
import com.hookedroid.androidarchitecture.api.model.Origin

object RoomConverters {
    @TypeConverter
    @JvmStatic
    fun fromOrigin(origin: Origin): String {
        return "${origin.name}||${origin.url}"
    }

    @TypeConverter
    @JvmStatic
    fun toOrigin(value: String): Origin {
        val info = value.split("||")
        return Origin(info[0], info[1])
    }

    @TypeConverter
    @JvmStatic
    fun fromStringArray(values: List<String>): String {
        return values.joinToString(separator = "||") { it }
    }

    @TypeConverter
    @JvmStatic
    fun toStringArray(value: String): List<String> {
        return value.split("||")
    }
}