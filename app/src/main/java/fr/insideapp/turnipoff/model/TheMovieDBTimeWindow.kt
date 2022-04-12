package fr.insideapp.turnipoff.model

import com.google.gson.annotations.SerializedName
import fr.insideapp.turnipoff.network.utils.EnumConvertible

enum class TheMovieDBTimeWindow(val value: String) : EnumConvertible {
    @SerializedName("day")
    Day("day"),

    @SerializedName("week")
    Week("week");

    override val jsonValue: String
        get() = this.value
}