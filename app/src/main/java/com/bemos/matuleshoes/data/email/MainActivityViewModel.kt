package com.bemos.matuleshoes.data.email

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.id_manager.IdManager

class MainActivityViewModel(
    private val emailManager: EmailManager,
    private val idManager: IdManager
) : ViewModel() {

    val state = MutableLiveData<String>()

    val stateId = MutableLiveData<String>()

    fun get() {
        val email = emailManager.get()
        state.value = email
    }

    fun set(email: String) {
        emailManager.set(email)
        state.value = email
    }

    fun getId() {
        val id = idManager.get()
        stateId.value = id
    }

    fun setId(id: String) {
        idManager.set(id)
        stateId.value = id
    }

}