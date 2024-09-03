package com.example.rickmorty.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.adapter.RickAdapter
import com.example.rickmorty.database.RickDataBase
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.repository.CharacterRepository
import com.example.rickmorty.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CharacterViewModel by viewModels() // Hilt-injected ViewModel
    private val adapter = RickAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewLayout.adapter = adapter

        // Observe the characters LiveData
        viewModel.characters.observe(this) { characters ->
            characters?.let {
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewLayout.visibility = View.VISIBLE
                adapter.submitList(it)
            }
        }

        // Observe error messages and show Toasts
        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewLayout.visibility = View.VISIBLE
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
