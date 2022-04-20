package fr.insideapp.turnipoff.model.person

import com.google.gson.annotations.SerializedName

enum class Gender {
    @SerializedName("0")
    Unknown,

    @SerializedName("1")
    Female,

    @SerializedName("2")
    Male
}