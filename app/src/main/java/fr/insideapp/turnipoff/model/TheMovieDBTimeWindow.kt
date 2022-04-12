package fr.insideapp.turnipoff.model

import com.google.gson.annotations.SerializedName

enum class TheMovieDBTimeWindow {
    @SerializedName("day")
    Day,
    @SerializedName("week")
    Week,
}