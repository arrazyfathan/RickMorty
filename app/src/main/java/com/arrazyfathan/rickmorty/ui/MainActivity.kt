package com.arrazyfathan.rickmorty.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arrazyfathan.rickmorty.R
import com.arrazyfathan.rickmorty.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("Timber has been planted")

        viewModel.character.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Timber.d(it.data.toString())
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Timber.d(it.message)
                }
            }
        }
    }
}
