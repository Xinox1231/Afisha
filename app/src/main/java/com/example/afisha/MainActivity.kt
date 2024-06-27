package com.example.afisha

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var rcViewMovies: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcViewMovies = findViewById(R.id.rcViewMovies)
        val adapter = MoviesAdapter()
        rcViewMovies.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.loadMovies(1)
        viewModel.moviesList.observe(this){
            adapter.addMovies(it)
        }
    }
}