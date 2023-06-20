package com.cybage.myapplication.views.repository

import com.cybage.myapplication.views.networkservice.RetrofitService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val retrofitService: RetrofitService) {
    fun getAllMovies(search_query :String) = retrofitService.getAllMovies(search_query)
}
