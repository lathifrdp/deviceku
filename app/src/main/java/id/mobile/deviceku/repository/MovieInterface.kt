package id.mobile.deviceku.repository

import id.mobile.deviceku.model.DeviceParameter
import id.mobile.deviceku.model.DeviceResponse
import id.mobile.deviceku.model.MovieModel
import id.mobile.deviceku.model.MovieParameter
import id.mobile.deviceku.model.MovieResponse
import id.mobile.deviceku.model.PostMovieResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {
    @GET("movie")
    suspend fun getListMovie(
        @Query("size") size: Int?,
        @Query("page") page: Int?
    ): MovieResponse

    @Multipart
    @POST("movie")
    suspend fun postMovie(
        @Part poster: MultipartBody.Part?,
        @Body data: MovieParameter?
    ): PostMovieResponse
}