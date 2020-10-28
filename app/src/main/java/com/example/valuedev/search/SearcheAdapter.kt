package com.example.valuedev.search



import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.valuedev.R
import com.example.valuedev.databinding.ItemDataBinding
import com.example.valuedev.developer.DetailDevActivity
import com.squareup.picasso.Picasso


class SearcheAdapter(val context: Context) : RecyclerView.Adapter<SearcheAdapter.SearchHolder>() {

    private val item = mutableListOf<ItemSearch>()

    companion object {
        const val ID_DEV = "ID_DEV"
    }

    fun addList(baris: List<ItemSearch>) {
        item.clear()
        item.addAll(baris)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_data, parent, false))
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val item = item[position]
        Picasso.get().load("http://3.85.146.25:3000/uploads/" + item.image).placeholder(R.drawable.blank_portrait).into(holder.binding.img)
        holder.binding.name.text = item.name
        holder.binding.jobDesk.text = item.job_desk
        holder.binding.status.text = item.status_job
        holder.binding.skill.text = item.skill
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailDevActivity::class.java)
            intent.putExtra(ID_DEV, item.id_bio_dev)
            context.startActivity(intent)
        }
    }

    class SearchHolder( val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root)
}



