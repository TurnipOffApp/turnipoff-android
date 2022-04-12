package fr.insideapp.turnipoff.model.movie

import com.google.gson.annotations.SerializedName

class Collection(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String
)