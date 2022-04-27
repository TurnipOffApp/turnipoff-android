package fr.insideapp.turnipoff.model

import com.google.gson.annotations.SerializedName
import fr.insideapp.turnipoff.network.utils.EnumConvertible

enum class TheMovieDBMovieGenre(private val value: String) : EnumConvertible {
    @SerializedName("28")
    Action("28"),

    @SerializedName("12")
    Adventure("12"),

    @SerializedName("16")
    Animation("16"),

    @SerializedName("35")
    Comedy("35"),

    @SerializedName("80")
    Crime("80"),

    @SerializedName("99")
    Documentary("99"),

    @SerializedName("18")
    Drame("18"),

    @SerializedName("10751")
    Familial("10751"),

    @SerializedName("14")
    Fantastique("14"),

    @SerializedName("36")
    History("36"),

    @SerializedName("27")
    Horror("27"),

    @SerializedName("10402")
    Music("10402"),

    @SerializedName("9648")
    Mystery("9648"),

    @SerializedName("10749")
    Romance("10749"),

    @SerializedName("878")
    ScienceFiction("878"),

    @SerializedName("10770")
    Telefilm("10770"),

    @SerializedName("53")
    Thriller("53"),

    @SerializedName("10752")
    War("10752"),

    @SerializedName("37")
    Western("37");

    override val jsonValue: String
        get() = this.value
}