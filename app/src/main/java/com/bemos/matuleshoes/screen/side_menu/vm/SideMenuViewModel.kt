package com.bemos.matuleshoes.screen.side_menu.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.Profile
import com.bemos.matuleshoes.data.supabase.profile.BaseProfileManager
import com.bemos.matuleshoes.screen.profile_screen.use_case.ProfileUseCase
import com.bemos.matuleshoes.screen.profile_screen.use_cases.ProfileUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SideMenuViewModel(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    val state = MutableLiveData<Profile>()

    fun getProfile(
        email: String
    ) {
        scope.launch {
            try {
                state.postValue(profileUseCase.invoke(email))
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

}