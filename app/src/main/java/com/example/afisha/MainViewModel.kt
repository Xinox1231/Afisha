package com.example.afisha

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.afisha.pojo.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    private val _isLoading = MutableLiveData<Unit>()
    val isLoading: LiveData<Unit>
        get() = _isLoading

    private val _isError = MutableLiveData<Unit>()
    val isError: LiveData<Unit>
        get() = _isLoading

    private val compositeDisposable = CompositeDisposable()

    fun loadMovies(page: Int) {
        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .map { it.movies }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _moviesList.value = it
                Log.d("MainViewModel", it.toString())
            }, {
                Log.d("MainViewModel", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}