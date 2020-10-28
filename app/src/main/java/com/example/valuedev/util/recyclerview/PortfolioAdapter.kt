package com.example.valuedev.util.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valuedev.R
import com.example.valuedev.databinding.ItemPortfolioBinding
import com.example.valuedev.util.model.PortfolioModel
import com.squareup.picasso.Picasso

class PortfolioAdapter(val items: ArrayList<PortfolioModel>, val listener: onClickViewListener) :
    RecyclerView.Adapter<PortfolioAdapter.portfolioViewHolder>() {

    fun addList(list: List<PortfolioModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    interface onClickViewListener {
        fun onClick(id: Int)
    }

    class portfolioViewHolder(val binding: ItemPortfolioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): portfolioViewHolder {
        return portfolioViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_portfolio,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: portfolioViewHolder, position: Int) {
        val picasso: Picasso
        val item = items[position]
        Picasso.get().load("http://3.85.146.25:3000/uploads/" + item.image).placeholder(R.drawable.blank_portrait).into(holder.binding.imagePortfolio)
        holder.binding.rlPortfolio.setOnClickListener {
            item.idPortfolio?.let { it1 -> listener.onClick(it1) }
        }
    }

    override fun getItemCount(): Int = items.size

}