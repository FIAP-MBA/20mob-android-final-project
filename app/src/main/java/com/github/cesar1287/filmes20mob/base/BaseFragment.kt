package com.github.cesar1287.filmes20mob.base

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment(), BaseView {

    override fun showLoading() {
        (activity as? BaseActivity)?.showLoading()
    }

    override fun hideLoading() {
        (activity as? BaseActivity)?.hideLoading()
    }
}