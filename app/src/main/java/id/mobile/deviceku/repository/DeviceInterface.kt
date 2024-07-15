package id.mobile.deviceku.repository

import id.mobile.deviceku.model.DeviceParameter
import id.mobile.deviceku.model.DeviceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DeviceInterface {
    @GET("objects")
    suspend fun getListDevice(): List<DeviceResponse>

    @GET("objects/{id}")
    suspend fun getDetailDevice(
        @Path("id") id: String
    ): DeviceResponse

    @POST("objects")
    suspend fun postDevice(
        @Body data: DeviceParameter?
    ): DeviceResponse
}