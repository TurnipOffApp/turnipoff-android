package fr.insideapp.turnipoff.network

import com.google.gson.annotations.SerializedName
import fr.insideapp.turnipoff.model.TheMovieDBMediaType
import fr.insideapp.turnipoff.model.TheMovieDBMovieGenre
import fr.insideapp.turnipoff.model.TheMovieDBResponse
import fr.insideapp.turnipoff.model.TheMovieDBTimeWindow
import fr.insideapp.turnipoff.model.movie.Movie
import fr.insideapp.turnipoff.model.movie.MovieCredits
import fr.insideapp.turnipoff.model.person.Person
import fr.insideapp.turnipoff.model.search.MovieSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service

interface TheMovieDBService : Service {
    @GET("trending/{media_type}/{time_window}")
    suspend fun trending(
        @Path(value = "media_type") mediaType: TheMovieDBMediaType,
        @Path(value = "time_window") timeWindow: TheMovieDBTimeWindow
    ): Response<TheMovieDBResponse<MovieSearchResult>>



    @GET("discover/movie")
    suspend fun discover(
        @Query(value = "sort_by") sortby: String = "vote_average.asc",
        @Query(value = "vote_count.gte") voteCount: Int = 25,
        @Query(value = "page") page: Int,
        @Query(value = "with_genres") genres: MutableList<TheMovieDBMovieGenre>? = null,
        @Query(value = "release_date.gte") releaseAfter: String? = null,
        @Query(value = "release_date.lte") releaseBefore: String? = null
    ): Response<TheMovieDBResponse<MovieSearchResult>>

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path(value = "movie_id") movieId: Long
    ): Response<Movie>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path(value = "movie_id") movieId: Long
    ): Response<MovieCredits>

    @GET("person/{person_id}")
    suspend fun getPerson(
        @Path(value = "person_id") personId: Long
    ): Response<Person>

    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonCredits(
        @Path(value = "person_id") personId: Long
    ): Response<MovieCredits>
}