package com.github.cesar1287.filmes20mob.base

import androidx.appcompat.app.AppCompatActivity
import com.github.cesar1287.filmes20mob.customView.LoadingDialog

open class BaseActivity: AppCompatActivity(), BaseView {
    private var loadingDialog: LoadingDialog? = null

    override fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.newInstance(this)
        }
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }
}