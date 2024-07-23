package id.mobile.deviceku.client

import id.mobile.deviceku.repository.DeviceInterface
import id.mobile.deviceku.repository.MovieInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieClient {
    private const val BASEURL = "https://dlabs-test.irufano.com/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieInterface: MovieInterface by lazy {
        retrofit.create(MovieInterface::class.java)
    }
}