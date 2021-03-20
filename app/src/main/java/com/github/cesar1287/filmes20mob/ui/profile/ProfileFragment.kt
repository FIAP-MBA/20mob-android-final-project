package com.github.cesar1287.filmes20mob.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.databinding.FragmentProfileBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment: Fragment() {
    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservables()
    }

    private fun setupView() {


        binding.profileAboutUsButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_aboutUsFragment)
        }
    }

    private fun setupObservables() {
        //TODO
    }
}