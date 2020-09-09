package com.example.movieapp.service

import com.example.movieapp.model.MovieResponse
import com.example.movieapp.model.PopularMovies
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL="https://api.themoviedb.org/"

object ServiceBuilder {

    private val client = OkHttpClient
        .Builder()
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(MovieAPI::class.java)

    fun buildService(): MovieAPI {
        return retrofit
    }


}