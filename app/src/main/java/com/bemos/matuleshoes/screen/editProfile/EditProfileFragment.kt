package com.bemos.matuleshoes.screen.editProfile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import coil.load
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.databinding.EditProfileFragmentBinding
import com.bemos.matuleshoes.screen.editProfile.vm.EditProfileViewModel

class EditProfileFragment: Fragment() {

    lateinit var binding: EditProfileFragmentBinding

    val editProfileViewModel = EditProfileViewModel(App.instance.baseProfileManager)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditProfileFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val emailManager = EmailManager(requireContext())
            editProfileViewModel.getProfile(emailManager.get())

            editProfileViewModel.state.observe(viewLifecycleOwner) {
                userImage.load(it.avatar)
                userNameTv.text = it.fullname

                username.setText(it.fullname)
                surname.setText(it.fullname)

            }

            updateProfilePhoto.setOnClickListener {
                pickImage()
            }

            saveChangedTv.setOnClickListener {
                editProfileViewModel.updateProfile(
                    username.text.toString(),
                    emailManager.get(),
                    App.image.toString()
                )
            }
        }
    }

    fun pickImage() {
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        startActivityForResult(intent, 101)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 101) {
                val uri = data?.data
                App.image = uri.toString()
                binding.userImage.setImageURI(uri)
            }
        }
    }

}