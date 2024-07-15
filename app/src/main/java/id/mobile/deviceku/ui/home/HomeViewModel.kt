package id.mobile.deviceku.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mobile.deviceku.model.DeviceResponse
import id.mobile.deviceku.repository.DeviceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    data object GetDeviceLoading : UiState()
    data class GetDeviceLoaded(val data: String) : UiState()
    data class GetDeviceError(val message: String) : UiState()
}

class HomeViewModel : ViewModel() {

    private val repository = DeviceRepository()

    private val _listDevice = MutableLiveData<List<DeviceResponse>>()
    val listDevice: LiveData<List<DeviceResponse>> = _listDevice

    private val _uiState = MutableStateFlow<UiState>(UiState.GetDeviceLoading)
    val uiState: StateFlow<UiState> = _uiState

    fun getListDevice() {
        viewModelScope.launch {
            try {
                delay(2000) // Simulating network delay
                val data = repository.getDevice()
                _listDevice.value = data
                _uiState.value = UiState.GetDeviceLoaded("Data fetched successfully")
            } catch (e: Exception) {
                // Handle error
                _uiState.value = UiState.GetDeviceError("Error fetching data")
                Log.e("getListDevice", e.message.toString());
            }
        }
    }
}