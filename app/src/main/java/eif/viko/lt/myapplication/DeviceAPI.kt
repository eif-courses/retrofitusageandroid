package eif.viko.lt.shop.api

import eif.viko.lt.myapplication.Device
import retrofit2.Call
import retrofit2.http.GET

interface DeviceAPI {
    @GET("lt.json") fun getDeviceList(): Call<List<Device>>
}