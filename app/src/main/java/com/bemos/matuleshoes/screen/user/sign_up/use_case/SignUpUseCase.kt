package com.bemos.matuleshoes.screen.user.sign_up.use_case

import android.util.Patterns
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager

class SignUpUseCase(
    private val baseAuthManager: BaseAuthManager
) {

    suspend operator fun invoke(
        fullname: String,
        email: String,
        password: String
    ) {
        if (fullname.isNotEmpty() && email.isNotEmpty() && (password.isNotEmpty() && password.length >= 8)) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                baseAuthManager.signUp(
                    fullname,
                    email,
                    password
                )
            }
        }
    }

}