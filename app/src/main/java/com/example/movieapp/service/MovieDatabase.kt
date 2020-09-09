package com.example.movieapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.model.Movie
import com.example.movieapp.model.PopularMovies

@Database(entities = [Movie::class],version = 1)
abstract class MovieDatabase :RoomDatabase() {

    abstract fun movieDao() : MovieDAO
    companion object {

        @Volatile  private var instance :MovieDatabase?=null

        private val lock  = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "moviedatabase"
        ).build()


    }
}