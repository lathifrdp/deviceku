package id.mobile.deviceku.client

import id.mobile.deviceku.repository.DeviceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    val baseUrl = "https://api.restful-api.dev/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val deviceInterface: DeviceInterface by lazy {
        retrofit.create(DeviceInterface::class.java)
    }
}