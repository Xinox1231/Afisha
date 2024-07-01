package com.example.afisha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afisha.pojo.Movie

class MoviesAdapter() : RecyclerView.Adapter<MovieHolder>() {

    var moviesList = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onReachEndListener: (() -> Unit)? = null
    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = moviesList[position]
        val rating = item.rating?.kp
        rating?.let {
            val ratingBackGroundId = if (it > 7.0) R.drawable.cicrle_green
            else if (it > 5) R.drawable.cicrle_yellow
            else R.drawable.cicrle_red
            holder.tvRating.background = ContextCompat.getDrawable(
                holder.itemView.context,
                ratingBackGroundId
            )
            holder.tvRating.text = String.format("%.1f", it)
        }
        Glide.with(holder.itemView).load(item.poster?.url).into(holder.ivPoster)
        if ((position == moviesList.size - 10) && onReachEndListener != null) {
            onReachEndListener?.invoke()
        }
        holder.itemView.setOnClickListener {
            if (onMovieClickListener != null) {
                onMovieClickListener?.invoke(moviesList[position])
            }
        }
    }
}

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
    val tvRating: TextView = itemView.findViewById(R.id.tvRating)
}