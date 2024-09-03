package com.example.rickmorty.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoClass {
    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): LiveData<List<Characters>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Characters>)

}