package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movieList:List<Movie>,
    @SerializedName("total_pages")
    val totalPage:Int,
    @SerializedName("total_results")
    val totalResult:Int
)