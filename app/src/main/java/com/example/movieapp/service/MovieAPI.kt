package com.example.movieapp.service

import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.model.PopularMovies
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    //https://api.themoviedb.org/3/movie/popular?api_key=d9d25ba60c3c37758dfa035b7f861397&page=1



    @GET("3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Single<PopularMovies>

}