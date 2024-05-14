package com.bemos.matuleshoes.screen.user.otp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OtpVerificationViewModel(
    private val baseAuthManager: BaseAuthManager
): ViewModel() {

    val stateError = MutableLiveData<String>()

    val scope = CoroutineScope(Dispatchers.IO)

    fun confirmOtp(
        token: String,
        email: String,
    ) {
        try {
            scope.launch {
                baseAuthManager.confirmOtp(
                    token,
                    email
                )
            }
        } catch (e: Exception) {
            stateError.value = e.message
        }
    }

    fun newPassword(
        email: String,
        password: String
    ) {
        try {
            scope.launch {
                baseAuthManager.newPassword(
                    email,
                    password
                )
            }
        } catch (e: Exception) {
            stateError.value = e.message
        }
    }

}