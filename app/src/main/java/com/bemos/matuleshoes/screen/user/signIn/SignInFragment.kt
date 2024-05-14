package com.bemos.matuleshoes.screen.user.signIn

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.EmptyFragment
import com.bemos.matuleshoes.MainActivity
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.connection.checkInternet
import com.bemos.matuleshoes.data.email.MainActivityViewModel
import com.bemos.matuleshoes.databinding.SignInFragmentBinding
import com.bemos.matuleshoes.databinding.SignUpFragmentBinding
import com.bemos.matuleshoes.screen.home.HomeFragment
import com.bemos.matuleshoes.screen.onBoarding.OnBoardingFragment
import com.bemos.matuleshoes.screen.user.forgotPassword.ForgotPasswordFragment
import com.bemos.matuleshoes.screen.user.signIn.vm.SignInViewModel
import com.bemos.matuleshoes.screen.user.signUp.SignUpFragment

class SignInFragment : Fragment(R.layout.sign_in_fragment) {

    private val binding: SignInFragmentBinding by viewBinding()

    val signInViewModel = SignInViewModel(App.instance.baseAuthManager)

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val mainActivity = activity as MainActivity

            mainActivityViewModel = mainActivity.mainActivityViewModel

            backImg.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, OnBoardingFragment())
                    .commit()
            }

            val connection = checkInternet(requireContext())

            enterInAccountBtn.setOnClickListener {
                if (connection) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                        if (password.length() != null) {
                            signInViewModel.signIn(
                                email.text.toString(),
                                password.text.toString()
                            )

                            mainActivityViewModel.set(email.text.toString())

                            parentFragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_container, HomeFragment())
                                .commit()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Internet Connection Invalid", Toast.LENGTH_SHORT).show()
                }
            }

            recoverTv.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, ForgotPasswordFragment())
                    .commit()
            }

            createUserTv.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, SignUpFragment())
                    .commit()
            }
        }
    }

}