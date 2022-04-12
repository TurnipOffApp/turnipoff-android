package fr.insideapp.turnipoff.model.search

import com.google.gson.annotations.SerializedName
import fr.insideapp.turnipoff.model.TheMovieDBMovieGenre
import java.time.LocalDate
import java.util.*

data class MovieSearchResult(
    @SerializedName("id")
    val id: Long,
    @SerializedName("adult")
    val isAdult: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("genre_ids")
    val genres: List<TheMovieDBMovieGenre>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("release_date")
    val releaseDate: LocalDate?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("video")
    val hasVideo: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)