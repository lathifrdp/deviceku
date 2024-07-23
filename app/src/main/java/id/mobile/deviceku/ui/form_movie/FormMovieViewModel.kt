package id.mobile.deviceku.ui.form_movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mobile.deviceku.model.MovieParameter
import id.mobile.deviceku.model.PostMovieResponse
import id.mobile.deviceku.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PostMovieUiState {
    data object PostMovieInitial : PostMovieUiState()
    data object PostMovieLoading : PostMovieUiState()
    data class PostMovieSuccess(val message: String) : PostMovieUiState()
    data class PostMovieError(val message: String) : PostMovieUiState()
}

class FormMovieViewModel : ViewModel() {
    private val repository = MovieRepository()
    private val _response = MutableLiveData<PostMovieResponse>()
    val response: LiveData<PostMovieResponse> = _response

    private val _uiState = MutableStateFlow<PostMovieUiState>(PostMovieUiState.PostMovieInitial)
    val uiState: StateFlow<PostMovieUiState> = _uiState

    fun postMovie(parameter: MovieParameter) {
        viewModelScope.launch {
            _uiState.value = PostMovieUiState.PostMovieLoading
            try {
                val data = repository.postDevice(parameter)
                _response.value = data
                _uiState.value = PostMovieUiState.PostMovieSuccess("Success Added Data")
                Log.e("postMovie", _response.value.toString())
            } catch (e: Exception) {
                // Handle error
                _uiState.value = PostMovieUiState.PostMovieError("Error Added Data")
                Log.e("postMovie", e.message.toString())
            }
        }
    }
}