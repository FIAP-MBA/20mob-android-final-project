package com.github.cesar1287.filmes20mob.utils

import android.util.Log
import com.github.cesar1287.filmes20mob.BuildConfig
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class RegisterEventsAnalytics {

    companion object {
        private val firebaseAnalytics = Firebase.analytics

        fun registerEvent(event: String) {
            if (BuildConfig.DEBUG) Log.d("ANALYTICS_EVENT", event)
            firebaseAnalytics.logEvent(event, null)
        }
    }
}