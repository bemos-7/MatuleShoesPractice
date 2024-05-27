package com.bemos.matuleshoes.screen.user.otp.use_case

import android.util.Patterns
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import kotlin.reflect.jvm.internal.impl.renderer.KeywordStringsGenerated

class OtpVerificationUseCase(
    private val baseAuthManager: BaseAuthManager
) {

    suspend operator fun invoke(
        token: String,
        email: String
    ) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && token.length == 6) {
            baseAuthManager.confirmOtp(
                token, email
            )
        }
    }

}