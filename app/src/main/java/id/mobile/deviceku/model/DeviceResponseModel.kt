package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName

class DeviceResponseModel (
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("createdAt")
    val createdAt: String? = null,
    @field:SerializedName("data")
    val data: DeviceModel? = null
)
