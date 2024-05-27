package com.bemos.matuleshoes.screen.user.otp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.databinding.OtpVerificationFragmentBinding
import com.bemos.matuleshoes.screen.user.forgot_password.ForgotPasswordFragment
import com.bemos.matuleshoes.screen.user.forgot_password.use_case.ForgotPasswordUseCase
import com.bemos.matuleshoes.screen.user.forgot_password.vm.ForgotPasswordViewModel
import com.bemos.matuleshoes.screen.user.otp.use_case.NewPasswordUseCase
import com.bemos.matuleshoes.screen.user.otp.use_case.OtpVerificationUseCase
import com.bemos.matuleshoes.screen.user.otp.use_cases.OtpUseCases
import com.bemos.matuleshoes.screen.user.otp.vm.OtpVerificationViewModel
import com.bemos.matuleshoes.screen.user.sign_in.SignInFragment

class OtpVerificationFragment : Fragment(R.layout.otp_verification_fragment) {

    private val binding: OtpVerificationFragmentBinding by viewBinding()

    val otpVerificationViewModel = OtpVerificationViewModel(
        OtpUseCases(
        OtpVerificationUseCase(App.instance.baseAuthManager),
        NewPasswordUseCase(App.instance.baseAuthManager)
        )
    )

    val forgotPasswordViewModel = ForgotPasswordViewModel(ForgotPasswordUseCase(App.instance.baseAuthManager))

    lateinit var timer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            val emailManager = EmailManager(requireContext())

            backImg.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, ForgotPasswordFragment())
                    .commit()
            }

            focusState(digestOne, digestTwo)
            focusState(digestTwo, digestThree)
            focusState(digestThree, digestFour)
            focusState(digestFour, digestFive)
            focusState(digestFive, digestSix)
            focusState(digestSix, digestSix)

            sendBtn.setOnClickListener {

                if(redColorDigest() == false) {
                    val token = "${digestOne.text}${digestTwo.text}${digestThree.text}${digestFour.text}${digestFive.text}${digestSix.text}"
                    otpVerificationViewModel.confirmOtp(token, App.email)

                    dialogShow()
                } else {
                    Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
                }

            }

            timer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val count = millisUntilFinished / 1000
                    binding.timeCount.text = "00:$count"
                }

                override fun onFinish() {
                    binding.timeCount.text = "00:00"
                    binding.resendTv.isEnabled = true
                }
            }.start()

            resendTv.setOnClickListener {
                forgotPasswordViewModel.forgotPassword(emailManager.get())
                resendTv.isEnabled = false
            }
        }
    }

    private fun dialogShow() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog_password)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val pass = dialog.findViewById<EditText>(R.id.password)
        val button = dialog.findViewById<Button>(R.id.save_btn)

        button.setOnClickListener {
            if (pass.text.isNotEmpty()) {
                otpVerificationViewModel.newPassword(
                    App.email,
                    pass.text.toString()
                )

                dialog.dismiss()

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, SignInFragment())
                    .commit()
            }
        }

        dialog.show()
    }

    fun focusState(digestFirst: EditText, digestSecond: EditText) {
        digestFirst.addTextChangedListener {
            val num = it.toString()
            if (num.length != null) {
                digestFirst.setBackgroundResource(R.drawable.input_style)
                digestSecond.requestFocus()
            }
        }
    }

    fun redColorDigest() : Boolean {
        isEmptyDigest(binding.digestOne)
        isEmptyDigest(binding.digestTwo)
        isEmptyDigest(binding.digestThree)
        isEmptyDigest(binding.digestFour)
        isEmptyDigest(binding.digestFive)
        isEmptyDigest(binding.digestSix)
        return isEmptyDigest(binding.digestOne) || isEmptyDigest(binding.digestTwo) || isEmptyDigest(binding.digestThree) || isEmptyDigest(binding.digestFour) || isEmptyDigest(binding.digestFour) || isEmptyDigest(binding.digestSix)
    }

    fun isEmptyDigest(digest: EditText) : Boolean {
        if (digest.text.isEmpty()) {
            digest.setBackgroundResource(R.drawable.input_style_red)
            return true
        }
        return false
    }

}