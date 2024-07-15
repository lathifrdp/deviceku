package id.mobile.deviceku.ui.form

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mobile.deviceku.model.DeviceParameter
import id.mobile.deviceku.model.DeviceResponse
import id.mobile.deviceku.repository.DeviceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PostDeviceUiState {
    data object PostDeviceInitial : PostDeviceUiState()
    data object PostDeviceLoading : PostDeviceUiState()
    data class PostDeviceSuccess(val message: String) : PostDeviceUiState()
    data class PostDeviceError(val message: String) : PostDeviceUiState()
}

class FormViewModel : ViewModel() {
    private val repository = DeviceRepository()
    private val _response = MutableLiveData<DeviceResponse>()
    val response: LiveData<DeviceResponse> = _response

    private val _uiState = MutableStateFlow<PostDeviceUiState>(PostDeviceUiState.PostDeviceInitial)
    val uiState: StateFlow<PostDeviceUiState> = _uiState

    fun postDevice(parameter: DeviceParameter) {
        viewModelScope.launch {
            _uiState.value = PostDeviceUiState.PostDeviceLoading
            try {
                val data = repository.postDevice(parameter)
                _response.value = data
                _uiState.value = PostDeviceUiState.PostDeviceSuccess("Success Added Data")
                Log.e("postDevice", _response.value.toString())
            } catch (e: Exception) {
                // Handle error
                _uiState.value = PostDeviceUiState.PostDeviceError("Error Added Data")
                Log.e("postDevice", e.message.toString())
            }
        }
    }
}