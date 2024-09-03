package com.example.rickmorty.di

import android.content.Context
import androidx.room.Room
import com.example.rickmorty.database.RickDataBase
import com.example.rickmorty.model.DaoClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideRickDataBase(@ApplicationContext context: Context): RickDataBase {
        return Room.databaseBuilder(context, RickDataBase::class.java,"dataBase.db").build()
    }

    @Provides
    fun provideCharacterDaoToDataBase(rickDataBase: RickDataBase): DaoClass {
        return rickDataBase.dao()
    }
}