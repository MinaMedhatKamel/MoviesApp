package com.mina.neugelb.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
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
        lifecycleScope.launch {
            delay(500)
            viewModel.fetchConfig()
                .collect {
                    when (it) {
                        is State.DataState -> {
                            binding.tvStatus.text = "success base URl ${it.data.images.base_url}"
                            Handler(Looper.getMainLooper()).postDelayed({
                                navigateToActivity(MainActivity::class.java)
                            }, 100)
                        }
                        is State.ErrorState -> binding.tvStatus.text = "error ${it.exception}"
                        is State.LoadingState -> binding.tvStatus.text = "loading"
                    }

                }
        }
    }
}