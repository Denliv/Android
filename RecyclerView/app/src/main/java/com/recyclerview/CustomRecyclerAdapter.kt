package com.recyclerview

import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private val names: List<String>) : RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder>() {
    private val imgs: IntArray = intArrayOf(
        R.drawable._n1,
        R.drawable._n2,
        R.drawable._n3,
        R.drawable._n4,
        R.drawable._n5,
        R.drawable._n6
    )
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val largeTextView: TextView = itemView.findViewById(R.id.textViewLarge)
        val smallTextView: TextView = itemView.findViewById(R.id.textViewSmall)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.largeTextView.text = names[position]
        holder.smallTextView.text = "Japan"
        holder.imageView.setImageResource(imgs[position])
    }


}