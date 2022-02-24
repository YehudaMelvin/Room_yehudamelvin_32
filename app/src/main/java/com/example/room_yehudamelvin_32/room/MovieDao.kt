package com.example.room_yehudamelvin_32.room

import android.graphics.Movie
import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    suspend fun addMovie(Movie: movie)

    @Update
    suspend fun updateMovie(Movie: movie)

    @Delete
    suspend fun deleteMovie(Movie: movie)

    @Query("SELECT * FROM movie")
    suspend fun getMovies():List<movie>

    @Query ("SELECT * FROM movie WHERE id=:movie_id")
    suspend fun getMovie(movie_id : Int): List<movie>
}