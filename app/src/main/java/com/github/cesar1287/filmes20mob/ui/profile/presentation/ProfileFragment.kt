package com.github.cesar1287.filmes20mob.ui.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseFragment
import com.github.cesar1287.filmes20mob.databinding.FragmentProfileBinding
import com.github.cesar1287.filmes20mob.utils.Command
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment: BaseFragment() {
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
        viewModel.command = command

        binding.createAccountButton.setOnClickListener {
            viewModel.loginUser()
        }

        binding.profileAboutUsButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_aboutUsFragment)
        }
    }

    private fun setupObservables() {
        viewModel.isUserLogged.observe(viewLifecycleOwner) { response ->

        }

        viewModel.command.observe(viewLifecycleOwner) {
            when(it) {
                is Command.Loading ->  {
                    if (it.value) showLoading()
                    else hideLoading()
                }
                is Command.Error -> {}
            }
        }

        viewModel.isUserLogged()
    }

    override var command: MutableLiveData<Command> = MutableLiveData()
}