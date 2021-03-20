package com.github.cesar1287.filmes20mob.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.github.cesar1287.filmes20mob.utils.Command

abstract class BaseFragment : Fragment() {

    abstract var command: MutableLiveData<Command>
}