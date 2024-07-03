package id.mobile.deviceku.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.mobile.deviceku.model.DeviceResponseModel
import id.mobile.deviceku.repository.DeviceRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    private val repository = DeviceRepository()

    private val _listDevice = MutableLiveData<List<DeviceResponseModel>>()
    val listDevice: LiveData<List<DeviceResponseModel>> = _listDevice

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
}