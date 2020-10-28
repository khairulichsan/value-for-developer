package com.example.valuedev.profile.port

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.valuedev.profile.ProfileService
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import kotlin.coroutines.CoroutineContext

class PortViewModel : ViewModel(), CoroutineScope {

    private lateinit var service: ProfileService

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main


    fun setLoginService(service: ProfileService) {
        this.service = service
    }

    fun postPort(name : RequestBody, desc : RequestBody, link_repo : RequestBody, link_publish : RequestBody,
                       place: RequestBody,type: RequestBody,idBio: RequestBody, image: MultipartBody.Part) {
        launch {

            val response = withContext(Dispatchers.IO) {
                try {
                    service.postPort(name, desc, link_repo, link_publish,place,type,idBio, image)
                } catch (e: Throwable) {
                  e.printStackTrace()

                }

            }

Log.d("respone", "${response is PortResponse}")
            if (response is PortResponse) {
                // Action Success
            }
        }
    }
}