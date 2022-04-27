package fr.insideapp.turnipoff.network

import com.google.gson.*
import fr.insideapp.turnipoff.network.utils.EnumAsOrdinalToStringConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


private interface Client<T: Service> {
    val service: T
}

object TheMovieDBClient: Client<TheMovieDBService> {
    private const val BASE_URL: String = "https://api.themoviedb.org/3/"
    private const val API_KEY: String = "7aeaa9d72de6df534afb8b71ac7d82eb"

    var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(LocalDate::class.java, object : JsonDeserializer<LocalDate?>, JsonSerializer<LocalDate?> {
                override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDate? {
                    return try {
                        if(json != null) {
                            LocalDate.parse(json.asString, formatter)
                        } else {
                            null
                        }
                    } catch (e: Exception) {
                        null
                    }
                }

                override fun serialize(src: LocalDate?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
                    return if(src != null) {
                        JsonPrimitive(src.format(formatter))
                    } else {
                        JsonNull.INSTANCE
                    }
                }
            })
            .create()
    }

    private val httpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpBuilder = OkHttpClient.Builder()

        okHttpBuilder.addInterceptor(logging)
        okHttpBuilder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = chain.request().url

            val url = originalHttpUrl
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("language", Locale.getDefault().toLanguageTag())
                .addQueryParameter("region", "US")//Locale.getDefault().country)
                .addQueryParameter("adult", "false")
                .build()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original
                .newBuilder()
                .url(url)

            val request = requestBuilder.build()
            return@addInterceptor chain.proceed(request)
        }

        return@lazy okHttpBuilder.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumAsOrdinalToStringConverterFactory())
            .build()
    }

    override val service: TheMovieDBService by lazy {
        retrofit.create(TheMovieDBService::class.java)
    }
}