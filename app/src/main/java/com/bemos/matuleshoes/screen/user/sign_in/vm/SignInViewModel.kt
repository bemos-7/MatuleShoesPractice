package com.bemos.matuleshoes.screen.user.sign_in.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import com.bemos.matuleshoes.screen.user.sign_in.use_case.SignInUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    val stateError = MutableLiveData<String>()

    val scope = CoroutineScope(Dispatchers.IO)

    fun signIn(
        email: String,
        password: String
    ) {
       scope.launch {
           try {
               signInUseCase.invoke(
                   email,
                   password
               )
           } catch (e: Exception) {
               stateError.postValue(e.message)
           }
       }
    }
}