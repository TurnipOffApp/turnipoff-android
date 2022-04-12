package fr.insideapp.turnipoff.model

import com.google.gson.annotations.SerializedName
import fr.insideapp.turnipoff.network.utils.EnumConvertible

enum class TheMovieDBMovieGenre(val value: String) : EnumConvertible {
    Action("28"),
    Adventure("12"),
    Animation("16"),
    Comedy("35"),
    Crime("80"),
    Documentary("99"),
    Drame("18"),
    Familial("10751"),
    Fantastique("14"),
    History("36"),
    Horror("27"),
    Music("10402"),
    Mystery("9648"),
    Romance("10749"),
    ScienceFiction("878"),
    Telefilm("10770"),
    Thriller("53"),
    War("10752"),
    Western("37");

    override val jsonValue: String
        get() = this.value
}