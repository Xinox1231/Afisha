package com.example.afisha.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.Serializable

@Entity("favourite_movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("year")
    @Expose
    var year: Int? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @Embedded
    @SerializedName("rating")
    @Expose
    var rating: Rating? = null,

    @Embedded
    @SerializedName("poster")
    @Expose
    var poster: Poster? = null,

    //@Embedded
    @SerializedName("videos")
    @Expose
    var videos: TrailerResponse? = null,


    ) : Serializable


class Converters {

    private val gson = Gson()

    // Преобразование из TrailerResponse в JSON строку
    @TypeConverter
    fun fromTrailerResponse(trailerResponse: TrailerResponse?): String? {
        return trailerResponse?.let { gson.toJson(it) }
    }

    // Преобразование из JSON строки в TrailerResponse
    @TypeConverter
    fun toTrailerResponse(json: String?): TrailerResponse? {
        return json?.let { gson.fromJson(it, TrailerResponse::class.java) }
    }

    // Преобразование из TrailersList в JSON строку
    @TypeConverter
    fun fromTrailersList(trailersList: TrailersList?): String? {
        return trailersList?.let { gson.toJson(it) }
    }

    // Преобразование из JSON строки в TrailersList
    @TypeConverter
    fun toTrailersList(json: String?): TrailersList? {
        return json?.let { gson.fromJson(it, TrailersList::class.java) }
    }

    // Преобразование из List<Trailer> в JSON строку
    @TypeConverter
    fun fromTrailers(trailers: List<Trailer>?): String? {
        return trailers?.let { gson.toJson(it) }
    }

    // Преобразование из JSON строки в List<Trailer>
    @TypeConverter
    fun toTrailers(json: String?): List<Trailer>? {
        val type = object : TypeToken<List<Trailer>>() {}.type
        return json?.let { gson.fromJson(it, type) }
    }
}
