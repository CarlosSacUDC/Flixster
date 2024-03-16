package com.example.flixster
import com.google.gson.annotations.SerializedName
class Movie {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var description : String? = null

    @SerializedName("poster_path")
    var posterLink : String? = null

}