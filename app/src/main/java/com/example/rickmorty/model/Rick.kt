package com.example.rickmorty.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.http.Url

data class Rick(
    val results: List<Characters>
)
@Entity("character_table")
data class Characters(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val image: String
)