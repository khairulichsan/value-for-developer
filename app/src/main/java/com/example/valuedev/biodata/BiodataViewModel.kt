package com.example.valuedev.biodata

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import kotlin.coroutines.CoroutineContext

class BiodataViewModel : ViewModel(), CoroutineScope {

    private lateinit var service: BiodataService

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main


    fun setLoginService(service: BiodataService) {
        this.service = service
    }

    fun postProjectApi(name : RequestBody, job : RequestBody, desk : RequestBody, city : RequestBody,
                       place: RequestBody,idDev: RequestBody,desc: RequestBody, image: MultipartBody.Part) {
        launch {

            val response = withContext(Dispatchers.IO) {
                try {
                    service.postBio(name, job, desk, city,place,idDev,desc, image)
                } catch (e: Throwable) {
                  e.printStackTrace()

                }

            }

Log.d("respone", "${response is BiodataResponse}")
            if (response is BiodataResponse) {
                // Action Success
            }
        }
    }
}