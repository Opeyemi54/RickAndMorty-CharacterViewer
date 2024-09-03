package com.example.rickmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickmorty.model.Characters
import com.example.rickmorty.model.DaoClass
import com.example.rickmorty.model.Rick

@Database(entities = [Characters::class], version = 1, exportSchema = false)
abstract class RickDataBase: RoomDatabase() {
    abstract fun dao(): DaoClass

}