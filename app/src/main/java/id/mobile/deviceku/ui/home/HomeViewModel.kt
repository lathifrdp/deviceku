package id.mobile.deviceku.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mobile.deviceku.model.DeviceResponseModel
import id.mobile.deviceku.repository.DeviceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: String) : UiState()
    data class Error(val message: String) : UiState()
}

class HomeViewModel : ViewModel() {

    private val repository = DeviceRepository()

    private val _listDevice = MutableLiveData<List<DeviceResponseModel>>()
    val listDevice: LiveData<List<DeviceResponseModel>> = _listDevice

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getListDevice() {
        viewModelScope.launch {
            try {
                delay(2000) // Simulating network delay
                val data = repository.getDevice()
                _listDevice.value = data
                _uiState.value = UiState.Success("Data fetched successfully")
            } catch (e: Exception) {
                // Handle error
                _uiState.value = UiState.Error("Error fetching data")
                Log.e("getListDevice", e.message.toString());
            }
        }
    }
}