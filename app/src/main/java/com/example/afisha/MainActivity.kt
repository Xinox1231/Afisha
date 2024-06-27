package com.example.afisha

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rcViewMovies: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        val adapter = MoviesAdapter()
        rcViewMovies.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        viewModel.moviesList.observe(this){
            adapter.submitList(it)
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

    private fun initViews(){
        rcViewMovies = findViewById(R.id.rcViewMovies)
        progressBar = findViewById(R.id.progressBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true // Если true - меню видно на экране
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemFavouriteMovies){
            val intent = FavouriteMoviesActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
