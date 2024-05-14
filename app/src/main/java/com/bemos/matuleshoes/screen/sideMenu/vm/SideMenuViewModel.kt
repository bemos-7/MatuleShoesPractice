package com.bemos.matuleshoes.screen.sideMenu.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.BaseAuthManager
import com.bemos.matuleshoes.data.supabase.auth.Profile
import com.bemos.matuleshoes.data.supabase.profile.BaseProfileManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SideMenuViewModel(
    private val baseProfileManager: BaseProfileManager
) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    val state = MutableLiveData<Profile>()

    fun getProfile(
        email: String
    ) {
        scope.launch {
            try {
                throw Exception()
                state.postValue(baseProfileManager.getProfile(email))
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }

}