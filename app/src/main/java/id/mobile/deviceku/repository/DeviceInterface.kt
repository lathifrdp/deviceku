package id.mobile.deviceku.repository

import id.mobile.deviceku.model.DeviceModel
import id.mobile.deviceku.model.DeviceResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DeviceInterface {
    @GET("objects")
    suspend fun getListDevice(): List<DeviceResponseModel>

    @GET("objects/{id}")
    suspend fun getDetailDevice(
        @Path("id") id: String
    ): DeviceResponseModel

    @POST("objects")
    suspend fun postDevice(
        @Body data: DeviceModel?
    ): DeviceResponseModel
}