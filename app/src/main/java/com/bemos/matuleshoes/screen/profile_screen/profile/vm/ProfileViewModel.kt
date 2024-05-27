package com.bemos.matuleshoes.screen.profile_screen.profile.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.Profile
import com.bemos.matuleshoes.data.supabase.profile.BaseProfileManager
import com.bemos.matuleshoes.screen.profile_screen.use_case.ProfileUseCase
import com.bemos.matuleshoes.screen.profile_screen.use_cases.ProfileUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileUseCases: ProfileUseCases
): ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    val state = MutableLiveData<Profile>()

    fun getProfile(
        email: String
    ) {
        scope.launch {
            try {
               state.postValue(profileUseCases.profileUseCase.invoke(email))
            }  catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

    fun updateProfile(
        fullname: String,
        email: String
    ) {
        scope.launch {
            try {
                profileUseCases.editProfileUseCase.invoke(
                    fullname, email
                )
            }  catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

}