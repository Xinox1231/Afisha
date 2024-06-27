package com.example.afisha

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afisha.pojo.Movie

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvYear: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivStar: ImageView

    private lateinit var rcViewTrailers: RecyclerView
    private lateinit var trailersAdapter: TrailerAdapter

    private lateinit var rcViewReviews: RecyclerView
    private lateinit var reviewsAdapter: ReviewsAdapter

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    companion object {

        const val EXTRA_MOVIE = "movie"
        private const val TAG = "MovieDetailsActivity"

        fun newInstance(context: Context, movie: Movie): Intent {
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

        viewModelFactory = MovieDetailViewModelFactory(application,movie.id)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailsViewModel::class.java]


        trailersAdapter = TrailerAdapter()
        rcViewTrailers.adapter = trailersAdapter
        viewModel.trailersList.observe(this)
        {
            trailersAdapter.trailerList = it
        }
        trailersAdapter.onTrailerClickListener =
            { trailer ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer.url)
                startActivity(intent)
            }

        reviewsAdapter = ReviewsAdapter()
        rcViewReviews.adapter = reviewsAdapter
        viewModel.reviewsList.observe(this)
        {
            reviewsAdapter.reviewsList = it
        }

        val starOf: Drawable? = ContextCompat.getDrawable(
            this@MovieDetailsActivity,
            android.R.drawable.star_big_off
        )
        val starOn: Drawable? = ContextCompat.getDrawable(
            this@MovieDetailsActivity,
            android.R.drawable.star_big_on
        )
        viewModel.favouriteMovies.observe(this) { movieFromDb ->
            if (movieFromDb == null) {
                ivStar.setImageDrawable(starOf)
                ivStar.setOnClickListener {
                    viewModel.insertMovie(movie)
                }
            }else{
                ivStar.setImageDrawable(starOn)
                ivStar.setOnClickListener {
                    viewModel.deleteMovie(movie.id)
                }
            }
        }
    }

    private fun initViews() {
        ivPoster = findViewById(R.id.ivPoster)
        tvTitle = findViewById(R.id.tvTitle)
        tvYear = findViewById(R.id.tvYear)
        tvDescription = findViewById(R.id.tvDescription)
        ivStar = findViewById(R.id.ivStar)

        rcViewTrailers = findViewById(R.id.rcViewTrailers)
        rcViewReviews = findViewById(R.id.rcViewReviews)
    }
}
