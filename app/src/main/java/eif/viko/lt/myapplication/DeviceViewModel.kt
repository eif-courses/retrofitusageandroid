package eif.viko.lt.shop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eif.viko.lt.myapplication.Device
import eif.viko.lt.shop.api.DeviceAPI
import eif.viko.lt.shop.repositories.DeviceRepository
import kotlinx.android.synthetic.main.device_list_item.view.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DeviceViewModel : ViewModel(){
    val list: MutableLiveData<List<Device>> = MutableLiveData()

    fun getDeviceList(): LiveData<List<Device>>{
        DeviceRepository.getInstance().create(DeviceAPI::class.java).getDeviceList().enqueue(object : Callback, retrofit2.Callback<List<Device>> {
            override fun onResponse(call: Call<List<Device>>, response: Response<List<Device>>) {
                if(response.isSuccessful){
                    list.postValue(response.body())
                }else{
                    list.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Device>>, t: Throwable) {
                list.postValue(null)
            }

        })
        return list
    }

}