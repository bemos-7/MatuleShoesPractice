package com.bemos.matuleshoes.screen.profile_screen.use_cases

import com.bemos.matuleshoes.screen.profile_screen.use_case.EditProfileUseCase
import com.bemos.matuleshoes.screen.profile_screen.use_case.ProfileUseCase

data class ProfileUseCases(
    val profileUseCase: ProfileUseCase,
    val editProfileUseCase: EditProfileUseCase
)