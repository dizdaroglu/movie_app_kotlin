package com.example.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PopularMovies(
    @SerializedName("results")
    val results: List<Movie>
)

@Entity
data class Movie(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val movieId: Int,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val poster_path: String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val release_date: String,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val vote_count: Int
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}