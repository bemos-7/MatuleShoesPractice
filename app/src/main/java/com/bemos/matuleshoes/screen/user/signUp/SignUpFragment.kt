package com.bemos.matuleshoes.screen.user.signUp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.connection.checkInternet
import com.bemos.matuleshoes.databinding.SignInFragmentBinding
import com.bemos.matuleshoes.databinding.SignUpFragmentBinding
import com.bemos.matuleshoes.screen.user.forgotPassword.ForgotPasswordFragment
import com.bemos.matuleshoes.screen.user.signIn.SignInFragment
import com.bemos.matuleshoes.screen.user.signIn.vm.SignInViewModel
import com.bemos.matuleshoes.screen.user.signUp.vm.SignUpViewModel

class SignUpFragment : Fragment(R.layout.sign_up_fragment) {

    private val binding: SignUpFragmentBinding by viewBinding()

    val signUpViewModel = SignUpViewModel(App.instance.baseAuthManager)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            backImg.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, SignInFragment())
                    .commit()
            }

            val connection = checkInternet(requireContext())

            registerBtn.setOnClickListener {
                if (connection) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {

                        if (username.text.length != null && password.text!!.length != null) {

                            if (checkBox.isChecked == true) {

                                signUpViewModel.signUp(
                                    username.text.toString(),
                                    email.text.toString(),
                                    password.text.toString()
                                )

                                parentFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.frame_container, SignInFragment())
                                    .commit()
                            } else {
                                Toast.makeText(requireContext(), "policy", Toast.LENGTH_SHORT).show()
                            }

                        }

                    }
                } else {
                    Toast.makeText(requireContext(), "Internet connection invalid", Toast.LENGTH_SHORT).show()
                }
                
            }

            signUpViewModel.stateError.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
            
            signInTv.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, SignInFragment())
                    .commit()
            }

            recoverTv.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, ForgotPasswordFragment())
                    .commit()
            }

        }
    }

}