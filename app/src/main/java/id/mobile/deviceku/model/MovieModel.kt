package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName

class MovieModel() {
    @field:SerializedName("id")
    var id: Int? = null

    @field:SerializedName("title")
    var title: String? = null

    @field:SerializedName("description")
    var description: String? = null

    @field:SerializedName("poster")
    var poster: String? = null

    @field:SerializedName("created_date")
    var createdDate: String? = null
}