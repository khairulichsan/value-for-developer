package com.example.valuedev.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valuedev.R
import com.example.valuedev.databinding.FragmentHomeBinding
import com.example.valuedev.util.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    companion object {
        const val ID_DEV = "ID_DEV"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)


            val RecyleAdapterr = context?.let { HomeAdapter(it) }
        useRetrofitToCallAPI()
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            binding.recyclerView.adapter = RecyleAdapterr

    }

    private fun useRetrofitToCallAPI() {

        val service = this.activity?.let { ApiClient.getApiClient(it)?.create(HomeService::class.java) }

        binding.progressBar.visibility = View.VISIBLE
        service?.getAllEmployee()?.enqueue(object : Callback<HomeResponse> {
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {

                Log.d("android1", "onFailure : ${Thread.currentThread().name}")
                Log.e("android1", t.message ?: "error")
            }

            override fun onResponse(
                call: Call<HomeResponse>,
                response: Response<HomeResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                Log.d("android1", "onSuccess : ${Thread.currentThread().name}")
                val list = response.body()?.data?.map {
                    ItemHome(it.id_bio_dev.orEmpty(),it.image.orEmpty(), it.name.orEmpty(), it.job_desk.orEmpty(), it.status_job.orEmpty(),it.skill.orEmpty())
                } ?: listOf()
                (binding.recyclerView.adapter as HomeAdapter).addList(list)


            }

        })
    }


    }


