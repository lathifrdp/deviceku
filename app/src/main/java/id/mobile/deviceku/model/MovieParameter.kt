package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName

class MovieParameter(
    @field:SerializedName("title")
    var title: String? = null,
    @field:SerializedName("description")
    var description: String? = null
)
