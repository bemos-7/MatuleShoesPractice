package com.bemos.matuleshoes.screen.user.otp.use_cases

import com.bemos.matuleshoes.screen.user.otp.use_case.NewPasswordUseCase
import com.bemos.matuleshoes.screen.user.otp.use_case.OtpVerificationUseCase

data class OtpUseCases(
    val otpVerificationUseCase: OtpVerificationUseCase,
    val newPasswordUseCase: NewPasswordUseCase
)