package fr.insideapp.turnipoff.model

import com.google.gson.annotations.SerializedName

data class TheMovieDBResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPage: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("results")
    val results: List<T>
)
