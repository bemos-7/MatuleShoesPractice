package com.bemos.matuleshoes.screen.user.sign_in.use_case

import android.util.Patterns
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager

class SignInUseCase(
    private val baseAuthManager: BaseAuthManager
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8) {
            baseAuthManager.signIn(
                email,
                password
            )
        }
    }

}