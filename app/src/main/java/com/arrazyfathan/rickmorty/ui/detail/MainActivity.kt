package com.arrazyfathan.rickmorty.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrazyfathan.rickmorty.databinding.ActivityMainBinding
import com.arrazyfathan.rickmorty.ui.MainViewModel
import com.arrazyfathan.rickmorty.ui.home.adapter.CharactersPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val adapter = CharactersPagerAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.charactersRecyclerview.adapter = adapter
        binding.charactersRecyclerview.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.getCharactersList().observe(this@MainActivity) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }

        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
            // binding.progressDialog.isVisible = true
                else {
                // binding.progressDialog.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }


        // observeViewModel()
    }

    /*private fun initUI(data: SingleCharacterResponse?) = with(binding) {
        if (data != null) {
            characterName.text = data.name
            characterStatus.text = data.status
            characterOriginName.text = data.origin.name
            characterSpeciesName.text = data.species

            Picasso.get().load(data.image).into(characterImage)
        }
    }*/

    /*private fun observeViewModel() = with(viewModel) {
        character.observe(this@MainActivity) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    initUI(response.data)
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Timber.d(response.message)
                }
            }
        }
    }*/
}
