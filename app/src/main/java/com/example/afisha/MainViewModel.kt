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

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isLoading

    private val compositeDisposable = CompositeDisposable()
    private var page: Int = 1

    init {
        loadMovies()
    }

    fun loadMovies() {
        val loading = _isLoading.value
        if(loading != null && loading) return
        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe{
                _isLoading.value = true
            }
            .map { it.movies?: listOf() }
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe({
                val loadedMovies : MutableList<Movie>? = _moviesList.value?.toMutableList()
                if(loadedMovies != null){
                    loadedMovies.addAll(it)
                    _moviesList.value = loadedMovies ?: listOf()
                }else{
                    _moviesList.value = it
                }
                page++
            }, {
                _isError.value = true
                Log.d("MainViewModel", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
