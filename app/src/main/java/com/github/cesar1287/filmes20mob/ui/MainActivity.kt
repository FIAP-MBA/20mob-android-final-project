package com.github.cesar1287.filmes20mob.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseActivity
import com.github.cesar1287.filmes20mob.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIfUserIsLoggedIn()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navController = Navigation.findNavController(this, R.id.host_fragment)
        setupWithNavController(binding.bottomNavigation, navController)
    }

    private fun checkIfUserIsLoggedIn() {
        if (Firebase.auth.currentUser == null) {
            signIn(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode != Activity.RESULT_OK) {
                signIn(this)
            }
        }
    }

    companion object {
        const val RC_SIGN_IN = 999

        fun signIn(context: Activity?) {
            // Choose authentication providers
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.AnonymousBuilder().build()
            )

            // Create and launch sign-in intent
            context?.startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setLogo(R.drawable.app_logo_512)
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN
            )
        }
    }
}