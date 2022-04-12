package fr.insideapp.turnipoff.network

import fr.insideapp.turnipoff.model.TheMovieDBMediaType
import fr.insideapp.turnipoff.model.TheMovieDBMovieGenre
import fr.insideapp.turnipoff.model.TheMovieDBResponse
import fr.insideapp.turnipoff.model.TheMovieDBTimeWindow
import fr.insideapp.turnipoff.model.movie.Movie
import fr.insideapp.turnipoff.model.movie.MovieCredits
import fr.insideapp.turnipoff.model.search.MovieSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate
import java.util.*

interface Service

interface TheMovieDBService : Service {
    @GET("trending/{media_type}/{time_window}")
    suspend fun trending(
        @Path(value = "media_type") mediaType: TheMovieDBMediaType,
        @Path(value = "time_window") timeWindow: TheMovieDBTimeWindow
    ): Response<TheMovieDBResponse<MovieSearchResult>>



    @GET("discover/movie")
    suspend fun discover(
        @Query(value = "sort_by") sortby: String,
        @Query(value = "vote_average") voteAverage: String,
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

    /*@GET("users")
    suspend fun getUsers(): Response<ApiResponse<List<User>>>

    @GET("suppliers")
    suspend fun getSuppliers(): Response<ApiResponse<List<Supplier>>>

    @GET("customer/{customerId}")
    suspend fun getCustomerById(@Path("customerId") customerId: Long): Response<ApiResponse<Customer>>

    @GET("supplier/{supplierId}")
    suspend fun getSupplierById(@Path("supplierId") supplierId: Long): Response<Supplier>*/
}