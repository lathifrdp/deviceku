package id.mobile.deviceku.model
import com.google.gson.annotations.SerializedName

class DeviceModel (
    @field:SerializedName("Color")
    val dataColor: String? = null,
    @field:SerializedName("Capacity")
    val dataCapacity: String? = null,
    @field:SerializedName("capacity GB")
    val capacityGB: Long? = null,
    @field:SerializedName("Price")
    val dataPrice: Double? = null,
    @field:SerializedName("Generation")
    val dataGeneration: String? = null,
    @field:SerializedName("year")
    val year: Long? = null,
    @field:SerializedName("CPU model")
    val cpuModel: String? = null,
    @field:SerializedName("Hard disk size")
    val hardDiskSize: String? = null,
    @field:SerializedName("Strap Colour")
    val strapColour: String? = null,
    @field:SerializedName("Case Size")
    val caseSize: String? = null,
    @field:SerializedName("color")
    val color: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("capacity")
    val capacity: String? = null,
    @field:SerializedName("Screen size")
    val screenSize: Double? = null,
    @field:SerializedName("generation")
    val generation: String? = null,
    @field:SerializedName("price")
    val price: String? = null
)