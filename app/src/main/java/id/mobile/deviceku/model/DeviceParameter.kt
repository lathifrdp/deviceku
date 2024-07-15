package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName

class DeviceParameter(
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("data")
    var data: DeviceModel? = null
)
