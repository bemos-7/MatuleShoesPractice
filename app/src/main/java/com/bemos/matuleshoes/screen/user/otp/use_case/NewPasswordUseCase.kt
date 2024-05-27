package com.bemos.matuleshoes.screen.user.otp.use_case

import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager

class NewPasswordUseCase(
    private val baseAuthManager: BaseAuthManager
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ) {
        if (password.length >= 8) {
            baseAuthManager.newPassword(
                email, password
            )
        }
    }

}