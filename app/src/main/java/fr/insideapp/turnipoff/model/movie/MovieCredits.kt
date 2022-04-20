package fr.insideapp.turnipoff.model.movie

import com.google.gson.annotations.SerializedName
import fr.insideapp.turnipoff.model.person.Gender

data class MovieCredits(
    @SerializedName("id")
    val id: Long,
    @SerializedName("cast")
    val cast: List<Credit>,
    @SerializedName("crew")
    val crew: List<Credit>

) {
    data class Credit(
        @SerializedName("id")
        val id: Long,
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("gender")
        val gender: Gender,
        @SerializedName("known_for_department")
        val knownForDepartment: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("profile_path")
        val profilePath: String?,
        @SerializedName("cast_id")
        val castID: Long,
        @SerializedName("character")
        val character: String? = "",
        @SerializedName("credit_id")
        val creditID: String,
        @SerializedName("order")
        val order: Long = 0,
        @SerializedName("department")
        val department: String = "",
        @SerializedName("job")
        val job: String? = ""
    ) {
        val subtitle: String
            get() = if(!character.isNullOrBlank()) {
                character
            } else if(!job.isNullOrBlank()) {
                job
            } else {
                "No role"
            }
    }
}