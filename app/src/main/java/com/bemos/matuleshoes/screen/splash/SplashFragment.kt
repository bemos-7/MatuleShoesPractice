package com.bemos.matuleshoes.screen.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.EmptyFragment
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.databinding.SignUpFragmentBinding
import com.bemos.matuleshoes.databinding.SplashFragmentBinding
import com.bemos.matuleshoes.screen.home.HomeFragment
import com.bemos.matuleshoes.screen.onBoarding.OnBoardingFragment
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty

class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val binding: SplashFragmentBinding by viewBinding()

    lateinit var timer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val emailManager = EmailManager(requireContext())

        timer = object : CountDownTimer(1000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
                val count = millisUntilFinished
            }

            override fun onFinish() {
                if (emailManager.get().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailManager.get()).matches()) {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, HomeFragment())
                        .commit()
                } else {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_container, OnBoardingFragment())
                        .commit()
                }
            }

        }.start()

    }

}