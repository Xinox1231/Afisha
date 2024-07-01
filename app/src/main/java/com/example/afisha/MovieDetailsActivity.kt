package com.example.afisha

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afisha.R
import com.example.afisha.databinding.ActivityMovieDetailsBinding
import com.example.afisha.pojo.Movie

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvYear: TextView
    private lateinit var tvDescription: TextView
    private lateinit var rcViewTrailers : RecyclerView
    private lateinit var trailersAdapter: TrailerAdapter
    private lateinit var viewModel: MovieDetailsViewModel

    companion object{

        const val EXTRA_MOVIE = "movie"
        fun newInstance(context: Context, movie: Movie): Intent{
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initViews()

        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
        Glide.with(this).load(movie.poster?.url).into(ivPoster)
        tvTitle.text = movie.name
        tvYear.text = movie.year.toString()
        tvDescription.text = movie.description

        trailersAdapter = TrailerAdapter()
        rcViewTrailers.adapter = trailersAdapter
        val trailer = movie.videos?.trailersList?.trailers
        Log.d("MainView", trailer.toString())

        viewModel = MovieDetailsViewModel()
        viewModel.getTrailers()
        viewModel.trailersList.observe(this){
            Log.d("Test", it.toString())
        }

    }

    private fun initViews(){
        ivPoster = findViewById(R.id.ivPoster)
        tvTitle = findViewById(R.id.tvTitle)
        tvYear = findViewById(R.id.tvYear)
        tvDescription = findViewById(R.id.tvDescription)
        rcViewTrailers = findViewById(R.id.rcViewTrailers)
    }
}