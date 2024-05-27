package com.bemos.matuleshoes.screen.profile_screen.use_case

import android.util.Patterns
import com.bemos.matuleshoes.data.supabase.profile.BaseProfileManager

class EditProfileUseCase(
    private val baseProfileManager: BaseProfileManager
) {

    suspend operator fun invoke(
        fullname: String,
        email: String,
        avatar: String? = null
    ) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && fullname.isNotEmpty()) {
            baseProfileManager.updateProfile(
                fullname, email
            )
        }
    }

}