package com.github.cesar1287.filmes20mob.ui.profile.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.model.Image
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
    private var permissionToRecordAccepted = false
    private var updatedImage: Image? = null

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
        binding.profileImage.setOnClickListener {
            imagePicker()
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

        viewModel.isProfileUpdated.observe(viewLifecycleOwner) { isUpdated ->
            if (isUpdated) {
                viewModel.loadUser()
            }
        }

        viewModel.command.observe(viewLifecycleOwner) {
            when(it) {
                is Command.Loading ->  {
                    if (it.value) showLoading()
                    else hideLoading()
                }
                is Command.Error -> Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.isUserLogged()
    }

    private fun imagePicker() {
        updatedImage = null
        ImagePicker.create(this)
            .returnMode(ReturnMode.ALL)
            .folderMode(false)
            .toolbarFolderTitle(getString(R.string.gallery))
            .toolbarArrowColor(Color.WHITE)
            .showCamera(true)
            .enableLog(true)
            .toolbarDoneButtonText(getString(R.string.done))
            .single()
            .start()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        permissionToRecordAccepted = requestCode == REQUEST_PERMISSION &&
                grantResults.getOrNull(0) == PackageManager.PERMISSION_GRANTED

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            ImagePicker.getFirstImageOrNull(data)?.let { image ->
                updatedImage = image
                context?.let {
                    Glide.with(it)
                        .load(image.path)
                        .placeholder(R.drawable.ic_default_person)
                        .into(binding.profileImage)
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun fillData(profile: Profile) {
        binding.profileNameEditText.setText(profile.name)
        binding.profileEmailEditText.setText(profile.email)
        if (profile.image != null && updatedImage == null) {
            context?.let {
                Glide.with(it)
                    .load(profile.image.toString())
                    .placeholder(R.drawable.ic_default_person)
                    .into(binding.profileImage)
            }
        }
    }

    private fun disableAndEnableItems(value: Boolean) {
        binding.profileImage.isClickable = value
        binding.profileNameEditText.isEnabled = value
        binding.profileInputLayoutName.isEnabled = value

        binding.createAccountButton.apply {
            text = if (value) {
                context?.getString(R.string.update_label)
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
            image = updatedImage
        )
    }

    private fun createUser() {
        MainActivity.signIn(activity)
    }

    override var command: MutableLiveData<Command> = MutableLiveData()

    companion object {
        const val REQUEST_PERMISSION = 200
    }
}