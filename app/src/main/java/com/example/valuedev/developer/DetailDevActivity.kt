package com.example.valuedev.developer


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.valuedev.R
import com.example.valuedev.databinding.ActivityDetailDevBinding
import com.example.valuedev.home.HomeFragment
import com.example.valuedev.util.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class DetailDevActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDevBinding
    private lateinit var dataDev : com.example.valuedev.developer.DevModel
    private lateinit var pagerAdapter: DevAdapter
    companion object {
        const val ID_DEV = "ID_DEV"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_dev)
        val id = intent.getStringExtra(HomeFragment.ID_DEV)?:""
        callSignApi(id)

        pagerAdapter = DevAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }

    private fun callSignApi(id:String) {

        val service = ApiClient.getApiClient(this)?.create(DevService::class.java)

        Log.d("cek lagi", "${id}")
        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
        coroutineScope.launch {

            val response = withContext(Dispatchers.IO) {

                service?.getDevRequest("${id}")?.enqueue(object : Callback<DevResponse> {
                    override fun onResponse(
                        call: Call<DevResponse>,
                        response: Response<DevResponse>
                    ) {


                            val data =response.body()?.data
                        binding.tvName.text = data?.name?:""
                        binding.tvDesk.text = data?.jobdesk?:""
                        binding.tvPlace.text = data?.place?:""
                        binding.tvStatus.text = data?.status?:""
                        binding.tvDesc.text = data?.desc?:""
                        binding.tvCity.text = data?.city?:""
                        Picasso.get().load("http://3.85.146.25:3000/uploads/${data?.image}").placeholder(R.drawable.blank_portrait)
                            .into(binding.dePhotoProfil)


                    }

                    override fun onFailure(
                        call: Call<DevResponse>,
                        t: Throwable
                    ) {
                        Log.d("onFailure", t.message.toString())
                        Log.d("profile", call.toString())
                    }
                    //
                })


            }
        }
    }




}





