package id.mobile.deviceku.model

import com.google.gson.annotations.SerializedName

class DeviceModel() {
    @field:SerializedName("Color")
    var dataColor: String? = null

    @field:SerializedName("Capacity")
    var dataCapacity: String? = null

    @field:SerializedName("capacity GB")
    var capacityGB: Long? = null

    @field:SerializedName("Price")
    var dataPrice: Double? = null

    @field:SerializedName("Generation")
    var dataGeneration: String? = null

    @field:SerializedName("year")
    var year: Long? = null

    @field:SerializedName("CPU model")
    var cpuModel: String? = null

    @field:SerializedName("Hard disk size")
    var hardDiskSize: String? = null

    @field:SerializedName("Strap Colour")
    var strapColour: String? = null

    @field:SerializedName("Case Size")
    var caseSize: String? = null

    @field:SerializedName("color")
    var color: String? = null

    @field:SerializedName("description")
    var description: String? = null

    @field:SerializedName("capacity")
    var capacity: String? = null

    @field:SerializedName("Screen size")
    var screenSize: Double? = null

    @field:SerializedName("generation")
    var generation: String? = null

    @field:SerializedName("price")
    var price: String? = null
}