package com.example.filmgenerator

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieInfo (
    var directors: Array<String>,
    @SerializedName("release_date") var releaseDate: Date,
    var rating: Float,
    var genres: Array<String>,
    @SerializedName("image_url") var imageURL: String,
    var plot: String,
    var rank: UByte,
    @SerializedName("running_time_secs") var runningTimeSecs: UInt,
    var actors: Array<String>,
)