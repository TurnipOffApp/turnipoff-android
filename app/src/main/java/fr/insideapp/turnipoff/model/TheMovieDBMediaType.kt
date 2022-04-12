package fr.insideapp.turnipoff.model

import com.google.gson.annotations.SerializedName
import fr.insideapp.turnipoff.network.utils.EnumConvertible

enum class TheMovieDBMediaType(val value: String) : EnumConvertible {
    @SerializedName("all")
    All("all"),

    @SerializedName("movie")
    Movie("movie"),

    @SerializedName("tv")
    Tv("tv"),

    @SerializedName("person")
    Person("person");

    override val jsonValue: String
        get() = this.value
}