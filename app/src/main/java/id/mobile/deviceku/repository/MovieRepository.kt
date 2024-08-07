package id.mobile.deviceku.repository

import id.mobile.deviceku.client.MovieClient
import id.mobile.deviceku.model.MovieParameter
import id.mobile.deviceku.model.MovieResponse
import id.mobile.deviceku.model.PostMovieResponse

class MovieRepository {
    private val api = MovieClient.movieInterface

    suspend fun getMovie(page: Int, size: Int): MovieResponse {
        return api.getListMovie(page = page, size = size)
    }

    suspend fun postDevice(parameter: MovieParameter): PostMovieResponse {
        return api.postMovie(poster = null, title = parameter.title, description = parameter.description)
    }
}