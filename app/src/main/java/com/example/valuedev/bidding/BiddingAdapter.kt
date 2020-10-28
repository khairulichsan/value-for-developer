package com.example.valuedev.bidding



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.valuedev.R
import com.example.valuedev.databinding.ItemBinddingBinding


class BiddingAdapter(val context: Context, var listener: OnAdapterListener): RecyclerView.Adapter<BiddingAdapter.HomeHolder>() {

    private val items = mutableListOf<ItemBidding>()


    fun addList(list: List<ItemBidding>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_bindding, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val item = items[position]
        holder.binding.name.text = item.job
        holder.binding.jobDesk.text = item.message
        holder.binding.status.text = item.price.toString() +".Rp"
        holder.binding.skill.text = item.created_at.toString()

        if (item.confirm.toString() == "1") {
            holder.binding.tvStatus.text = "Agree"
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_status1)
        } else if (item.confirm.toString() == "0") {
            holder.binding.tvStatus.text = "Waiting for Confirm"
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_status0)
        } else {
            holder.binding.tvStatus.text = "Not Agree"
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_status2)
        }

        holder.binding.agree.setOnClickListener {
            listener.onUpdate1(item)
        }

        holder.binding.notAgree.setOnClickListener {
            listener.onUpdate2(item)
        }

    }

    class HomeHolder(val binding: ItemBinddingBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnAdapterListener {
        fun onUpdate1(item: ItemBidding)
        fun onUpdate2(item: ItemBidding)
    }
}



