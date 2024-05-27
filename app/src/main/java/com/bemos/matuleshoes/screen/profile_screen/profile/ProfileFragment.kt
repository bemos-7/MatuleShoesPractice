package com.bemos.matuleshoes.screen.profile_screen.profile

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.connection.checkInternet
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.databinding.ProfileFragmentBinding
import com.bemos.matuleshoes.screen.profile_screen.edit_profile.EditProfileFragment
import com.bemos.matuleshoes.screen.profile_screen.profile.vm.ProfileViewModel
import com.bemos.matuleshoes.screen.profile_screen.use_case.EditProfileUseCase
import com.bemos.matuleshoes.screen.profile_screen.use_case.ProfileUseCase
import com.bemos.matuleshoes.screen.profile_screen.use_cases.ProfileUseCases

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val binding: ProfileFragmentBinding by viewBinding()

    val profileViewModel = ProfileViewModel(
        ProfileUseCases(
            ProfileUseCase(App.instance.baseProfileManager),
            EditProfileUseCase(App.instance.baseProfileManager)
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val emailManager = EmailManager(requireContext())
            profileViewModel.getProfile(emailManager.get())

            profileViewModel.state.observe(viewLifecycleOwner) {
                userImage.load(it.avatar)
                username.setText(it.fullname)
                email.setText(it.email)
            }
            
            val internet = checkInternet(requireContext())

            saveBtn.setOnClickListener {
                if (internet) {
                    val username = username.text.toString()
                    val email = email.text.toString()

                    profileViewModel.updateProfile(username, email)
                    Toast.makeText(requireContext(), "Данные были обновлены", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Internet Connection Invalid", Toast.LENGTH_SHORT).show()
                }
            }

            editProfileImg.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, EditProfileFragment())
                    .commit()
            }

        }
    }

}