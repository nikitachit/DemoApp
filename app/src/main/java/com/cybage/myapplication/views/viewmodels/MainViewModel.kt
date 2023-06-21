package com.cybage.myapplication.views.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybage.myapplication.views.models.Movie
import com.cybage.myapplication.views.models.MovieList
import com.cybage.myapplication.views.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>(false)

    fun getAllMovies(search_query: String) {

        loading.postValue(true)
        val response = repository.getAllMovies(search_query)

        response.enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.body()!!.results.isEmpty()) {
                    errorMessage.postValue("No matching movies found")
                } else {
                    errorMessage.postValue("")
                    movieList.postValue(response.body()!!.results)
                }
                loading.postValue(false)
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                errorMessage.postValue(t.message)
                loading.postValue(false)

            }
        })
    }
}