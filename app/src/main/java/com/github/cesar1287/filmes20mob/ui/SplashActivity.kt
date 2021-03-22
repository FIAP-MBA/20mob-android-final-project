package com.github.cesar1287.filmes20mob.ui

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cesar1287.filmes20mob.databinding.ActivitySplashBinding
import com.github.cesar1287.filmes20mob.utils.Constants.Firebase.KEY_REMOTE_CONFIG_APP_NAME
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSplashAppName.text = Firebase.remoteConfig.getString(KEY_REMOTE_CONFIG_APP_NAME)

        binding.lavAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) { /** NOT USED*/ }

            override fun onAnimationEnd(p0: Animator?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) { /** NOT USED*/ }

            override fun onAnimationRepeat(p0: Animator?) { /** NOT USED*/ }

        })
    }
}