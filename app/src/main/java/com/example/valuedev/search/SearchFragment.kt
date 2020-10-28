package com.example.valuedev.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valuedev.R
import com.example.valuedev.databinding.FragmentSearchBinding
import com.example.valuedev.util.ApiClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

private lateinit var binding: FragmentSearchBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var searchFor = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)


        val RecyleAdapterr = context?.let { SearcheAdapter(it) }

        binding.searchBar.addTextChangedListener(object : TextWatcher {


            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                searchFor = searchText
                val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
                coroutineScope.launch {
                    delay(300)  //debounce timeOut
                    if (searchText != searchFor)
                        return@launch

                    useRetrofitToCallAPI(searchFor)
                }


            }
        })


        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = RecyleAdapterr

    }

    private fun useRetrofitToCallAPI(search: String?) {
        val service = this.activity?.let { ApiClient.getApiClient(it)?.create(SearchService::class.java) }

        if (search != null) {
            service?.getSearch(
                search,
                "city",
                1
            )?.enqueue(object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {

                    Log.d("android1", "onFailure : ${Thread.currentThread().name}")
                    Log.e("android1", t.message ?: "error")
                }

                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    Log.d("android1", "onSuccess : ${Thread.currentThread().name}")
                    val baris = response.body()?.data?.map {
                        ItemSearch(it.id_bio_dev.orEmpty(),it.image.orEmpty(), it.name.orEmpty(), it.job_desk.orEmpty(), it.status_job.orEmpty(),it.skill.orEmpty())
                    } ?: listOf()
                    (binding.recyclerView.adapter as SearcheAdapter).addList(baris)
                }


            })
        }
    }



}



