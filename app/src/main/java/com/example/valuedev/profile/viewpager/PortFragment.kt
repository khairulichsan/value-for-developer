package com.example.valuedev.profile.viewpager

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
import com.example.ticketapps.util.sharedpref.Constant
import com.example.valuedev.R
import com.example.valuedev.databinding.FragmentPortfolioBinding
import com.example.valuedev.home.HomeFragment
import com.example.valuedev.util.ApiClient
import com.example.valuedev.util.model.PortfolioModel
import com.example.valuedev.util.recyclerview.PortfolioAdapter
import com.example.valuedev.util.response.PortfolioResponse
import com.example.valuedev.util.service.PortfolioApiService
import com.example.valuedev.util.sharedpref.SharedPrefProvider
import kotlinx.coroutines.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PortFragment : Fragment() {
    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var defaultPref : SharedPrefProvider
    private var list: List<PortfolioModel> = listOf()
    private lateinit var RecyclerAdapter: PortfolioAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio, container, false)
        defaultPref = SharedPrefProvider(requireContext())
        setUpRecycler()
        callPortfolioApi()
        return binding.root
    }

    private fun callPortfolioApi() {
        val service = ApiClient.getApiClient(activity as AppCompatActivity)
            ?.create(PortfolioApiService::class.java)
        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

        coroutineScope.launch {
            val response = withContext(Job() + Dispatchers.IO) {
                try {
                    service?.getAllPortfolioById(
                            defaultPref.getString(Constant.KEY_ID_BIO)!!.toInt()
                    )
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            Log.d("checking", "port : ${response is PortfolioResponse}")
            if (response is PortfolioResponse) {
                list = response.data?.map {
                    PortfolioModel(it.idPortfolio, it.imagePortfolio.orEmpty())
                } ?: listOf()
                (binding.rvPortfolio.adapter as PortfolioAdapter).addList(list)
            } else {
                Toast.makeText(activity as AppCompatActivity, "Failed Get Data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setUpRecycler() {
        RecyclerAdapter =
            PortfolioAdapter(arrayListOf(), object : PortfolioAdapter.onClickViewListener {
                override fun onClick(id: Int) {
                    Toast.makeText(activity as AppCompatActivity, "Clicked!", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        binding.rvPortfolio.adapter = RecyclerAdapter
        binding.rvPortfolio.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }
}