package com.bemos.matuleshoes.screen.user.forgotPassword

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.connection.checkInternet
import com.bemos.matuleshoes.databinding.ForgotPasswordFragmentBinding
import com.bemos.matuleshoes.databinding.SignUpFragmentBinding
import com.bemos.matuleshoes.screen.user.forgotPassword.vm.ForgotPasswordViewModel
import com.bemos.matuleshoes.screen.user.otp.OtpVerificationFragment
import com.bemos.matuleshoes.screen.user.signIn.SignInFragment

class ForgotPasswordFragment : Fragment(R.layout.forgot_password_fragment) {

    private val binding: ForgotPasswordFragmentBinding by viewBinding()

    val forgotPasswordViewModel = ForgotPasswordViewModel(App.instance.baseAuthManager)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            backImg.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, SignInFragment())
                    .commit()
            }

            val connection = checkInternet(requireContext())

            sendBtn.setOnClickListener {
                if (connection) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                        forgotPasswordViewModel.forgotPassword(email.text.toString())
                        App.email = email.text.toString()
                        showDialog()
                    }
                } else {
                    Toast.makeText(requireContext(), "Internet Connection Invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val card = dialog.findViewById<LinearLayout>(R.id.dialog_card)

        card.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, OtpVerificationFragment())
                .commit()
            dialog.dismiss()
        }
        dialog.show()
    }
}