package com.example.valuedev.bidding

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketapps.util.sharedpref.Constant
import com.example.valuedev.R
import com.example.valuedev.databinding.FragmentBiddingBinding
import com.example.valuedev.util.ApiClient
import com.example.valuedev.util.sharedpref.SharedPrefProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class BiddingFragment : Fragment() {

    private lateinit var binding : FragmentBiddingBinding
    private lateinit var defaultPref : SharedPrefProvider

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bidding, container, false)
        defaultPref = SharedPrefProvider(requireContext())
        return binding.root
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)


        val RecyleAdapterr = BiddingAdapter(requireContext(), object : BiddingAdapter.OnAdapterListener {
            override fun onUpdate1(item: ItemBidding) {
                Update1(item)
            }

            override fun onUpdate2(item: ItemBidding) {
                Update2(item)
            }
        })
        useRetrofitToCallAPI()

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = RecyleAdapterr

    }

    private fun useRetrofitToCallAPI() {

        val service = this.activity?.let { ApiClient.getApiClient(it)?.create(BiddingService::class.java) }

        binding.progressBar.visibility = View.VISIBLE
        val idBio = defaultPref.getString(Constant.KEY_ID_BIO)?:""
        service?.getBidding(100, "$idBio")?.enqueue(object : Callback<BiddingResponse> {
            override fun onFailure(call: Call<BiddingResponse>, t: Throwable) {

                Log.d("android1", "onFailure : ${Thread.currentThread().name}")
                Log.e("android1", t.message ?: "error")
            }

            override fun onResponse(
                    call: Call<BiddingResponse>,
                    response: Response<BiddingResponse>
            ) {
                binding.progressBar.visibility = View.GONE

                Log.d("android1", "onSuccess : ${Thread.currentThread().name}")
                val list = response.body()?.data?.map {
                    ItemBidding(it.idHire.toString(), it.job.orEmpty(), it.message.orEmpty(), it.price.toString(), it.confirm.orEmpty(), it.created_at.orEmpty())
                } ?: listOf()
                (binding.recyclerView.adapter as BiddingAdapter).addList(list)


            }

        })
    }

                private fun Update1(item: ItemBidding){

                    val service = ApiClient.getApiClient(requireContext())?.create(BiddingService::class.java)
                    service?.patchBidding(item.idHire, "1")?.enqueue(object : Callback<Confirm> {

                        override fun onResponse(call: Call<Confirm>, response: Response<Confirm>) {
                            Toast.makeText(requireContext(), "You Agree", Toast.LENGTH_SHORT).show()
                            useRetrofitToCallAPI()
                        }
                        override fun onFailure(call: Call<Confirm>, t: Throwable) {
                            Toast.makeText(requireContext(), "You Agree", Toast.LENGTH_SHORT).show()
                            useRetrofitToCallAPI()
                        }

                    })

                }
    private fun Update2(item: ItemBidding){

        val service = ApiClient.getApiClient(requireContext())?.create(BiddingService::class.java)
        service?.patchBidding(item.idHire, "2")?.enqueue(object : Callback<Confirm> {

            override fun onResponse(call: Call<Confirm>, response: Response<Confirm>) {
                Toast.makeText(requireContext(), "You Not Agree", Toast.LENGTH_SHORT).show()
                useRetrofitToCallAPI()
            }
            override fun onFailure(call: Call<Confirm>, t: Throwable) {
                Toast.makeText(requireContext(), "You Not Agree", Toast.LENGTH_SHORT).show()
                useRetrofitToCallAPI()
            }

        })

    }


}



