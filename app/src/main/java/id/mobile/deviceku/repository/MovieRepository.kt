package id.mobile.deviceku.repository

import id.mobile.deviceku.client.MovieClient
import id.mobile.deviceku.model.MovieParameter
import id.mobile.deviceku.model.MovieResponse
import id.mobile.deviceku.model.PostMovieResponse

class MovieRepository {
    private val api = MovieClient.movieInterface

    suspend fun getMovie(): MovieResponse {
        return api.getListMovie(page = 1, size = 10)
    }

    suspend fun postDevice(parameter: MovieParameter): PostMovieResponse {
        return api.postMovie(data = parameter, poster = null)
    }
}