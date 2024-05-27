package com.bemos.matuleshoes.screen.user.sign_up

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.connection.checkInternet
import com.bemos.matuleshoes.databinding.SignUpFragmentBinding
import com.bemos.matuleshoes.screen.user.forgot_password.ForgotPasswordFragment
import com.bemos.matuleshoes.screen.user.sign_in.SignInFragment
import com.bemos.matuleshoes.screen.user.sign_up.use_case.SignUpUseCase
import com.bemos.matuleshoes.screen.user.sign_up.vm.SignUpViewModel

class SignUpFragment : Fragment(R.layout.sign_up_fragment) {

    private val binding: SignUpFragmentBinding by viewBinding()

    val signUpViewModel = SignUpViewModel(SignUpUseCase(App.instance.baseAuthManager))

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
                    if (checkBox.isChecked == true) {

                        val name = username.text.toString()
                        val email = email.text.toString()
                        val password = password.text.toString()

                        signUpViewModel.signUp(
                            name,
                            email,
                            password
                        )

                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_container, SignInFragment())
                            .commit()
                    } else {
                        Toast.makeText(requireContext(), "policy", Toast.LENGTH_SHORT).show()
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