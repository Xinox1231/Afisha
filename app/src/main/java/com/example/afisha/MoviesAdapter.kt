package com.example.afisha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.afisha.pojo.Movie

class MoviesAdapter() : ListAdapter<Movie, MovieHolder>(MovieItemDiffCallback()) {

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

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = getItem(position)
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
        Glide.with(holder.itemView).load(item.poster?.url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(holder.ivPoster)
        if ((position == itemCount - 10) && onReachEndListener != null) {
            onReachEndListener?.invoke()
        }
        holder.itemView.setOnClickListener {
            onMovieClickListener?.invoke(getItem(position))
        }
    }
}

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
    val tvRating: TextView = itemView.findViewById(R.id.tvRating)
}
