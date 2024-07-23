package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName

class PostMovieResponse (
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("data")
    val data: String? = null,
    @field:SerializedName("info")
    val info: String? = null
)