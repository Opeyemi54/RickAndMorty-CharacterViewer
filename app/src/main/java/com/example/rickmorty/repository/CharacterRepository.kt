package com.example.rickmorty.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.rickmorty.di.IoDispatcher
import com.example.rickmorty.model.Characters
import com.example.rickmorty.model.DaoClass
import com.example.rickmorty.model.GetRick
import dagger.Module
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterRepository @Inject constructor(
    private val characterDao: DaoClass,
    private val getRickService: GetRick,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher // Injected Dispatcher
) {

    fun getAllCharactersFromDb(): LiveData<List<Characters>> {
        return characterDao.getAllCharacters()
    }

    suspend fun refreshCharacters(): Boolean {
        return withContext(ioDispatcher) {
            try {
                val response = getRickService.getAllCharacter()
                characterDao.insertAll(response.results)
                true
            } catch (e: Exception) {
                Log.d("repositoryError", "refreshCharacters: ${e.message}")
                false
            }
        }
    }
}

