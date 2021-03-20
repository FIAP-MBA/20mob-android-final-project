package com.github.cesar1287.filmes20mob.ui.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.github.cesar1287.filmes20mob.R
import com.github.cesar1287.filmes20mob.base.BaseFragment
import com.github.cesar1287.filmes20mob.databinding.FragmentProfileBinding
import com.github.cesar1287.filmes20mob.model.Profile
import com.github.cesar1287.filmes20mob.ui.MainActivity
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

        binding.profileAboutUsButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_aboutUsFragment)
        }
    }

    private fun setupObservables() {
        viewModel.isUserLogged.observe(viewLifecycleOwner) { isUserLogged ->
            disableAndEnableItems(isUserLogged)
            if (isUserLogged) {
                viewModel.loadUser()
            }
        }

        viewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            fillData(profile)
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
    }

    override fun onResume() {
        super.onResume()
        viewModel.isUserLogged()
    }

    private fun fillData(profile: Profile) {
        binding.profileNameEditText.setText(profile.name)
        binding.profileEmailEditText.setText(profile.email)
    }

    private fun disableAndEnableItems(value: Boolean) {
        binding.profileImage.isClickable = value
        binding.profileNameEditText.isEnabled = value
        binding.profileInputLayoutName.isEnabled = value
        binding.profileBioEditText.isEnabled = value
        binding.profileInputLayoutBio.isEnabled = value

        binding.createAccountButton.apply {
            text = if (value) {
                context?.getString(R.string.save_label)
            } else context?.getString(R.string.create_account_label
            )
            setOnClickListener {
                if (value) {
                    updateUser()
                } else {
                    createUser()
                }
            }
        }
    }

    private fun updateUser() {
        viewModel.updateUser(
            name = binding.profileNameEditText.text.toString(),
            bio = binding.profileBioEditText.text.toString(),
            image = ""
        )
    }

    private fun createUser() {
        MainActivity.signIn(activity)
    }

    override var command: MutableLiveData<Command> = MutableLiveData()
}