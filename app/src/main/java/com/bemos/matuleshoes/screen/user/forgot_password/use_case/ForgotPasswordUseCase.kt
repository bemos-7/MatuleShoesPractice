package com.bemos.matuleshoes.screen.user.forgot_password.use_case

import android.util.Patterns
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager

class ForgotPasswordUseCase(
    private val baseAuthManager: BaseAuthManager
) {

    suspend operator fun invoke(
        email: String
    ) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            baseAuthManager.forgotPassword(
                email
            )
        }
    }

}