package com.example.afisha

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class FavouriteMoviesActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rcViewMovies: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var viewModel: FavoriteMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        rcViewMovies.adapter = moviesAdapter
        viewModel = ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]
        viewModel.favouriteMoviesList.observe(this) {
            moviesAdapter.submitList(it)
        }
        moviesAdapter.onMovieClickListener = {
            val intent = MovieDetailsActivity.newInstance(this, it)
            startActivity(intent)
        }
    }

    private fun initViews() {
        rcViewMovies = findViewById(R.id.rcViewMovies)
        progressBar = findViewById(R.id.progressBar)
        moviesAdapter = MoviesAdapter()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FavouriteMoviesActivity::class.java)
        }
    }
}