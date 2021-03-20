package com.github.cesar1287.filmes20mob.customView

import android.app.Activity
import android.os.Bundle
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseDialog

class LoadingDialog(activity: Activity) : BaseDialog(activity, R.style.full_screen_dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.progress_bar_indeterminate_dialog)
        setCancelable(false)
    }

    companion object {
        fun newInstance(activity: Activity): LoadingDialog {
            return LoadingDialog(activity)
        }
    }

}