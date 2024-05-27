package com.bemos.matuleshoes.screen.profile_screen.use_case

import android.util.Patterns
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import com.bemos.matuleshoes.data.supabase.auth.Profile
import com.bemos.matuleshoes.data.supabase.profile.BaseProfileManager
import com.bemos.matuleshoes.screen.profile_screen.profile.vm.ProfileViewModel

class ProfileUseCase(
    private val baseProfileManager: BaseProfileManager
) {

    suspend operator fun invoke(
        email: String
    ): Profile {
        val profile = baseProfileManager.getProfile(email)
        return profile
    }

}