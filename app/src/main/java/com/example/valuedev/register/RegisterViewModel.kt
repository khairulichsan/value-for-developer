package com.example.valuedev.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RegisterViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isRegisterLiveData = MutableLiveData<Boolean>()
    val isMessageLiveData = MutableLiveData<String>()

    private lateinit var service: RegisterApiService
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setRegisterService(service: RegisterApiService) {
        this.service = service
    }

    fun callRegisterApi(name: String?, email: String?, password: String?, phoneNumber: String?) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.registerRequest(name, email, password, phoneNumber)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    isRegisterLiveData.value = false
                }
            }
            if (response is RegisterResponse) {
                isMessageLiveData.value = response.message
                isRegisterLiveData.value = true
            }
            isLoadingLiveData.value = false
        }
    }
}