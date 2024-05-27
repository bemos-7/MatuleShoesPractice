package com.bemos.matuleshoes.screen.user.otp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import com.bemos.matuleshoes.screen.user.otp.use_cases.OtpUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OtpVerificationViewModel(
    private val otpUseCases: OtpUseCases
): ViewModel() {

    val stateError = MutableLiveData<String>()

    val scope = CoroutineScope(Dispatchers.IO)

    fun confirmOtp(
        token: String,
        email: String,
    ) {
        scope.launch {
            try {
                otpUseCases.otpVerificationUseCase.invoke(
                    token,
                    email
                )
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

    fun newPassword(
        email: String,
        password: String
    ) {
        scope.launch {
            try {
                otpUseCases.newPasswordUseCase.invoke(
                    email,
                    password
                )
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

}