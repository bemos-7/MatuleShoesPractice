package com.bemos.matuleshoes.screen.user.signUp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val baseAuthManager: BaseAuthManager
) : ViewModel() {

    val stateError = MutableLiveData<String>()

    val scope = CoroutineScope(Dispatchers.IO)

    fun signUp(
        fullname: String,
        email: String,
        password: String
    ) {
        try {
            scope.launch {
                baseAuthManager.signUp(
                    fullname,
                    email,
                    password
                )
            }
        } catch (e: Exception) {
            stateError.value = e.message
        }
    }

}