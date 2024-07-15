package id.mobile.deviceku.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mobile.deviceku.model.DeviceResponse
import id.mobile.deviceku.repository.DeviceRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val repository = DeviceRepository()
    private val _listDevice = MutableLiveData<List<DeviceResponse>>()
    val listDevice: LiveData<List<DeviceResponse>> = _listDevice

    fun getListDevice() {
        viewModelScope.launch {
            try {
                val data = repository.getDevice()
                _listDevice.value = data
            } catch (e: Exception) {
                // Handle error
                Log.e("getListDevice", e.message.toString());
            }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}