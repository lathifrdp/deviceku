package id.mobile.deviceku.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    val baseUrl = "https://api.restful-api.dev/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}