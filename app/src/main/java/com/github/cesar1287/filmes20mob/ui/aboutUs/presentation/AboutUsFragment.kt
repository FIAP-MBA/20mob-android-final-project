package com.github.cesar1287.filmes20mob.ui.aboutUs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.cesar1287.filmes20mob.databinding.FragmentAboutUsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AboutUsFragment: Fragment() {
    private val viewModel: AboutUsViewModel by viewModel()
    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        binding.aboutUsVersionText.text = setVersionApp()

        binding.aboutUsBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setVersionApp(): String {
        activity?.let {
            return it.packageManager.getPackageInfo(it.packageName, 0).versionName
        }
        return "-"
    }
}