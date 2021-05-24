package com.github.cesar1287.filmes20mob.base

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.Window

abstract class BaseDialog(
    var activity: Activity,
    themeResId: Int
) : Dialog(activity, themeResId) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun show() {
        try {
            if (!activity.isFinishing) {
                super.show()
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.toString())
        }
    }

    override fun dismiss() {
        try {
            if (!activity.isFinishing) {
                super.dismiss()
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.toString())
        }
    }

    override fun cancel() {
        try {
            super.cancel()
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.toString())
        }
    }
}