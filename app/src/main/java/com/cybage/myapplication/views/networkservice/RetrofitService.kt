package com.cybage.myapplication.views.networkservice

import com.cybage.myapplication.views.models.MovieList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    companion object {

        var client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer ${Constants.API_ACESS_TOKEN}")
                .build()
            chain.proceed(newRequest)
        }.build()


        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API_SERVICE_PATH)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

    @GET("movie")
    fun getAllMovies(@Query("query")query : String): Call<MovieList>

}