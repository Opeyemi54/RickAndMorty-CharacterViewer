package com.example.rickmorty.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.model.Characters
import com.example.rickmorty.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val characters: LiveData<List<Characters>> = repository.getAllCharactersFromDb()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            // Attempt to refresh data from the API
            val success = repository.refreshCharacters()
            if (!success) {
                _error.postValue("Failed to refresh data. Please check your internet connection.")
            }
        }
    }
}







