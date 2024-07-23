package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody

class MovieParameter(
    @field:SerializedName("title")
    var title: RequestBody? = null,
    @field:SerializedName("description")
    var description: RequestBody? = null
)
