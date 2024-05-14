package com.bemos.matuleshoes.screen.favorite.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.data.supabase.card.BaseFavoriteManager
import com.bemos.matuleshoes.data.supabase.card.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val baseFavoriteManager: BaseFavoriteManager
) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    val state = MutableLiveData<List<Product>>()

    fun getFavorite(
        id: String
    ) {
        try {
            scope.launch {
                state.postValue(baseFavoriteManager.getItems(id))
            }
        } catch (e: Exception) {
            stateError.value = e.message
        }
    }

}