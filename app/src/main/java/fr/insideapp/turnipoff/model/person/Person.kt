package fr.insideapp.turnipoff.model.person

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Person(
    @SerializedName("id")
    val id: Long,
    @SerializedName("imdb_id")
    val imdbID: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("also_known_as")
    val otherNames: List<String>,
    @SerializedName("biography")
    val biography: String?,
    @SerializedName("deathday")
    val deathday: LocalDate?,
    @SerializedName("birthday")
    val birthday: LocalDate?,
    @SerializedName("known_for_department")
    val department: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("gender")
    val gender: Gender,
)