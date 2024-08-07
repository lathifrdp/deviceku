package id.mobile.deviceku.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mobile.deviceku.model.MovieModel
import id.mobile.deviceku.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class MovieUiState {
    data object GetMovieLoading : MovieUiState()
    data class GetMovieLoaded(val data: String) : MovieUiState()
    data class GetMovieError(val message: String) : MovieUiState()
}

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()
    private val _listMovie = MutableLiveData<List<MovieModel>>()
    val listMovie: LiveData<List<MovieModel>> = _listMovie

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.GetMovieLoading)
    val uiState: StateFlow<MovieUiState> = _uiState

    private var currentPage = 1
    var isLoading = false
    private var hasMoreData = true

    fun getListMovie() {
        if (isLoading || !hasMoreData) return

        isLoading = true

        viewModelScope.launch {
            try {
                //delay(2000) // Simulating network delay
                val data = repository.getMovie(page = currentPage, size = 100)
                val currentList = _listMovie.value ?: emptyList()
                _listMovie.value = currentList + (data.data ?: emptyList())
                _uiState.value = MovieUiState.GetMovieLoaded("Data fetched successfully")
                currentPage++
                hasMoreData = data.data?.isNotEmpty() == true
            } catch (e: Exception) {
                // Handle error
                _uiState.value = MovieUiState.GetMovieError("Error fetching data")
                Log.e("getMovieDevice", e.message.toString());
            } finally {
                isLoading = false
            }
        }
    }
}