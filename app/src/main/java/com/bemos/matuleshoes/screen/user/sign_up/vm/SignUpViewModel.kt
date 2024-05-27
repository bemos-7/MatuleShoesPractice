package com.bemos.matuleshoes.screen.user.sign_up.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import com.bemos.matuleshoes.screen.user.sign_up.use_case.SignUpUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val stateError = MutableLiveData<String>()

    val scope = CoroutineScope(Dispatchers.IO)

    fun signUp(
        fullname: String,
        email: String,
        password: String
    ) {
        scope.launch {
            try {
                signUpUseCase.invoke(
                    fullname,
                    email,
                    password
                )
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

}