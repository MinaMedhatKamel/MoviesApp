package com.mina.neugelb.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mina.neugelb.ui.MainActivity
import com.mina.neugelb.databinding.ActivitySplashBinding
import com.mina.neugelb.ui.State
import com.mina.neugelb.ui.base.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : BaseActivity() {
    private val viewModel: SplashViewModel by viewModel()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getConfigFromCache()

    }

    private fun getConfigFromCache() {
        lifecycleScope.launch {
            delay(500)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getCachedData()
                    .collect {
                        when (it) {
                            is State.DataState -> {
                                binding.tvStatus.text = "success base URl ${it.data?.imgBaseUrlHQ}"
                                navigateTomHome()
                            }
                            is State.ErrorState -> {
                                binding.tvStatus.text = "error ${it.exception.message}"
                                FetchConfig()
                            }
                            is State.LoadingState -> binding.tvStatus.text = "loading from cache"
                        }
                    }
            }
        }
    }

    private fun FetchConfig() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchConfig()
                    .collect {
                        when (it) {
                            is State.DataState -> {
                                binding.tvStatus.text =
                                    "success base URl ${it.data.images.base_url}"
                                navigateTomHome()
                            }
                            is State.ErrorState -> {
                                binding.tvStatus.text = "error ${it.exception}"
                                navigateTomHome()
                            }
                            is State.LoadingState -> binding.tvStatus.text = "loading from network"
                        }
                    }
            }
        }
    }

    private fun navigateTomHome() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToActivity(MainActivity::class.java)
        }, 100)
    }
}