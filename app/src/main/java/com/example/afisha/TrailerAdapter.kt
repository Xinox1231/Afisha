package com.example.afisha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.afisha.pojo.Trailer

class TrailerAdapter(): RecyclerView.Adapter<TrailerViewHolder>() {

    var trailerList: List<Trailer> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.trailer_item,
            parent,
            false
        )
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = trailerList[position]
        holder.tvTrailer.text = trailer.name
    }
}

class TrailerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val tvTrailer: Button = itemView.findViewById(R.id.tvTrailer)
}