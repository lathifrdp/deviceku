package id.mobile.deviceku.repository

import id.mobile.deviceku.client.Client
import id.mobile.deviceku.model.DeviceModel
import id.mobile.deviceku.model.DeviceResponseModel

class DeviceRepository {
    private val api = Client.deviceInterface

    suspend fun getDevice(): List<DeviceResponseModel> {
        return api.getListDevice()
    }

    suspend fun getDetailDevice(id: String): DeviceResponseModel {
        return api.getDetailDevice(id = id)
    }

    suspend fun postDevice(deviceModel: DeviceModel): DeviceResponseModel {
        return api.postDevice(data = deviceModel)
    }
}