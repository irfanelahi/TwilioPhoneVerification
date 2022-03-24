package com.irfandev.twiliointegration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.irfandev.twiliointegration.Backend.ServerResponse
import com.irfandev.twiliointegration.ViewModels.Repository
import kotlin.collections.HashMap
/**
 *Irfan Elahi Dev
 */
class VerificationViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: Repository? = null
    var serverLiveData: MutableLiveData<ServerResponse>? = null

        private set

    fun init() {
        repository = Repository()
        serverLiveData = repository!!.liveData
    }

    fun verifyPhone(values: HashMap<String, String>) {
        repository!!.verifyPhone(values)
    }
}