package id.mobile.deviceku.repository

import id.mobile.deviceku.client.Client
import id.mobile.deviceku.model.DeviceParameter
import id.mobile.deviceku.model.DeviceResponse

class DeviceRepository {
    private val api = Client.deviceInterface

    suspend fun getDevice(): List<DeviceResponse> {
        return api.getListDevice()
    }

    suspend fun getDetailDevice(id: String): DeviceResponse {
        return api.getDetailDevice(id = id)
    }

    suspend fun postDevice(parameter: DeviceParameter): DeviceResponse {
        return api.postDevice(data = parameter)
    }
}