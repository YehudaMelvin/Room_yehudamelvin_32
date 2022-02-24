package com.example.room_yehudamelvin_32.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class movie (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tittle: String,
    val desc: String
    )
