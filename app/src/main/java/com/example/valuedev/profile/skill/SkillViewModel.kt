package com.example.valuedev.profile.skill

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valuedev.profile.ProfileService
import com.example.valuedev.register.RegisterApiService
import com.example.valuedev.register.RegisterResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SkillViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isRegisterLiveData = MutableLiveData<Boolean>()
    val isMessageLiveData = MutableLiveData<String>()

    private lateinit var service: ProfileService
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setRegisterService(service: ProfileService) {
        this.service = service
    }

    fun callSkillApi(id_bio: String?, skill: String?) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.postSkill(id_bio, skill)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    isRegisterLiveData.value = false
                }
            }
            if (response is SkillResponse) {
                isMessageLiveData.value = response.message
                isRegisterLiveData.value = true
            }
            isLoadingLiveData.value = false
        }
    }
}