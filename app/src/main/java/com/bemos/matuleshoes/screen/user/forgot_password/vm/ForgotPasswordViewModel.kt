package com.bemos.matuleshoes.screen.user.forgot_password.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import com.bemos.matuleshoes.screen.user.forgot_password.use_case.ForgotPasswordUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
): ViewModel() {

    val stateError = MutableLiveData<String>()

    val scope = CoroutineScope(Dispatchers.IO)

    fun forgotPassword(
        email: String
    ) {
        scope.launch {
            try {
                forgotPasswordUseCase.invoke(
                    email
                )
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

}