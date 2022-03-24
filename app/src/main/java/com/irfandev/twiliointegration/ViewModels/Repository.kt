package com.irfandev.twiliointegration.ViewModels


import androidx.lifecycle.MutableLiveData
import com.irfandev.twiliointegration.Backend.ServerResponse
import com.irfandev.twiliointegration.Backend.RetroService
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 *Irfan Elahi Dev
 */

class Repository {
    private val retroService: RetroService
    val liveData: MutableLiveData<ServerResponse>?

    fun verifyPhone(values: HashMap<String, String>) {
        retroService.sendVerificationCode(values)
            .enqueue(object : Callback<ServerResponse?> {
                override fun onResponse(
                    call: Call<ServerResponse?>,
                    response: Response<ServerResponse?>
                ) {
                    if (response.body() != null) {
                        liveData?.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                    liveData?.postValue(null)
                }
            })
    }


    companion object {
        private const val BASE = "URL"
    }

    init {
        liveData = MutableLiveData()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        retroService = Retrofit.Builder()
            .baseUrl(BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetroService::class.java)
    }
}