package com.bemos.matuleshoes.screen.editProfile.vm

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.auth.Profile
import com.bemos.matuleshoes.data.supabase.profile.BaseProfileManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val baseProfileManager: BaseProfileManager
) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    val state = MutableLiveData<Profile>()

    fun getProfile(
        email: String
    ) {
        try {
            scope.launch {
                state.postValue(baseProfileManager.getProfile(email))
            }
        }  catch (e: Exception) {
            stateError.value = e.message
        }
    }

    fun updateProfile(
        fullname: String,
        email: String,
        avatar: String
    ) {
        try {
            scope.launch {
                baseProfileManager.updateProfileSecond(fullname, avatar, email)
            }
        }  catch (e: Exception) {
            stateError.value = e.message
        }
    }

}