package eif.viko.lt.shop.repositories
import eif.viko.lt.shop.api.DeviceAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val BASE_URL = "https://muziejus-api.firebaseio.com/"
class DeviceRepository {
    companion object {
        fun getInstance():Retrofit {
            return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        }
        //val api: DeviceAPI by lazy { retrofit.create(DeviceAPI::class.java) }
    }
}