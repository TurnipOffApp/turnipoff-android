package fr.insideapp.turnipoff.model

import com.google.gson.annotations.SerializedName

enum class TheMovieDBMediaType {
    @SerializedName("all")
    All,
    @SerializedName("movie")
    Movie,
    @SerializedName("tv")
    Tv,
    @SerializedName("person")
    Person
}