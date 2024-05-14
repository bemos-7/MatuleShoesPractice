package com.bemos.matuleshoes.screen.user.forgotPassword.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val baseAuthManager: BaseAuthManager
): ViewModel() {

    val stateError = MutableLiveData<String>()

    val scope = CoroutineScope(Dispatchers.IO)

    fun forgotPassword(
        email: String,
    ) {
        try {
            scope.launch {
                baseAuthManager.forgotPassword(
                    email
                )
            }
        } catch (e: Exception) {
            stateError.value = e.message
        }
    }

}