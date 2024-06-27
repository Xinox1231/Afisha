package com.example.afisha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afisha.pojo.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter() : RecyclerView.Adapter<MovieHolder>() {

    private val moviesList = mutableListOf<Movie>()

    fun addMovies(movies: List<Movie>) {
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

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
        val rating = String.format("%.1f", item.rating?.kp)
        holder.tvRating.text = rating
        Glide.with(holder.itemView).load(item.poster?.url).into(holder.ivPoster)
    }
}

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
    val tvRating: TextView = itemView.findViewById(R.id.tvRating)
}