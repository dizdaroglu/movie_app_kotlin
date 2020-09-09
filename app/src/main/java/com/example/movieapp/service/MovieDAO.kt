package com.example.movieapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.model.Movie

@Dao
interface MovieDAO {

    @Insert
    suspend fun insertAll(vararg movie:Movie) :List<Long>

    @Query("SELECT * FROM movie")
    suspend fun getAllMovie():List<Movie>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    suspend fun getMovie(movieId:Int) :Movie

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovie()
}