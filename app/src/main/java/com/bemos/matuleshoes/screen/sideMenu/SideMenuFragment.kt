package com.bemos.matuleshoes.screen.sideMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets.Side
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.databinding.SideMenuFragmentBinding
import com.bemos.matuleshoes.screen.notification.NotificationFragment
import com.bemos.matuleshoes.screen.profile.ProfileFragment
import com.bemos.matuleshoes.screen.sideMenu.vm.SideMenuViewModel

class SideMenuFragment: Fragment() {

    lateinit var binding: SideMenuFragmentBinding

    val sideMenuViewModel = SideMenuViewModel(App.instance.baseProfileManager)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SideMenuFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            val emailManager = EmailManager(requireContext())

            sideMenuViewModel.getProfile(emailManager.get())

            sideMenuViewModel.state.observe(viewLifecycleOwner) {
                usernameTv.text = it.fullname
                userImage.load(it.avatar)
            }

            sideMenuViewModel.stateError.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it?:"it", Toast.LENGTH_SHORT).show()
            }

            profileBtn.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, ProfileFragment())
                    .commit()
            }

            notificationBtn.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, NotificationFragment())
                    .commit()
            }

        }
    }

}