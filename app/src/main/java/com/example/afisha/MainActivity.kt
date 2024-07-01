package com.example.afisha

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rcViewMovies: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcViewMovies = findViewById(R.id.rcViewMovies)
        progressBar = findViewById(R.id.progressBar)
        val adapter = MoviesAdapter()
        rcViewMovies.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.moviesList.observe(this){
            adapter.moviesList = it
        }
        viewModel.isLoading.observe(this){
            if (it == false){
                progressBar.visibility = View.GONE
            }
            else{
                progressBar.visibility = View.VISIBLE
        }
            }
        adapter.onReachEndListener = {
            viewModel.loadMovies()
        }
        adapter.onMovieClickListener = { movie ->
            val intent = MovieDetailsActivity.newInstance(this, movie)
            startActivity(intent)
        }
    }
}