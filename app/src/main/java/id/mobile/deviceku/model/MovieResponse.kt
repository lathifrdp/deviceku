package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName

class MovieResponse (
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("data")
    val data: List<MovieModel>? = null,
    @field:SerializedName("info")
    val info: String? = null
)