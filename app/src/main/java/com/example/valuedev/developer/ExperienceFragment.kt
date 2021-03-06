package com.example.valuedev.developer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valuedev.R
import com.example.valuedev.databinding.FragmentExperienceBinding
import com.example.valuedev.home.HomeFragment
import com.example.valuedev.util.ApiClient
import com.example.valuedev.util.model.ExperienceModel
import com.example.valuedev.util.recyclerview.ExperienceAdapter
import com.example.valuedev.util.response.ExperienceResponse
import com.example.valuedev.util.service.ExperienceApiService
import kotlinx.coroutines.*

class ExperienceFragment : Fragment() {
    private lateinit var binding: FragmentExperienceBinding
    private var list: List<ExperienceModel> = listOf()
    private lateinit var RecyclerAdapter: ExperienceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_experience, container, false)
        callPortfolioApi()
        setUpRecycler()
        return binding.root
    }

    private fun callPortfolioApi() {
        val service = ApiClient.getApiClient(activity as AppCompatActivity)?.create(
            ExperienceApiService::class.java
        )
        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
        Log.d("checkEx", coroutineScope.toString())

        coroutineScope.launch {
            val response = withContext(Job() + Dispatchers.IO) {
                try {
                    service?.getAllExperienceById(
                        (activity as AppCompatActivity).intent.getStringExtra(HomeFragment.ID_DEV
                        )!!.toInt()
                    )
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            Log.d("check", (response is ExperienceResponse).toString())
            if (response is ExperienceResponse) {
                Log.d("check", response.data.toString())

                list = response.data?.map {
                    ExperienceModel(
                        it.idExperience,
                        it.companyName,
                        it.description,
                        it.workPosition,
                        it.start,
                        it.end,
                        it.idDev
                    )
                } ?: listOf()
                (binding.rvExperience.adapter as ExperienceAdapter).addList(list)
            } else {
                Toast.makeText(activity as AppCompatActivity, "Failed Data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setUpRecycler() {
        RecyclerAdapter = ExperienceAdapter(arrayListOf())
        binding.rvExperience.adapter = RecyclerAdapter
        binding.rvExperience.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
    }
}