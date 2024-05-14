package com.bemos.matuleshoes.screen.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.card.BaseFavoriteManager
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val baseFavoriteManager: BaseFavoriteManager
) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    fun putFavorite(
        image: String,
        name: String,
        price: String,
        id: String
    ) {
        try {
            scope.launch {
                baseFavoriteManager.putItem(
                    image,
                    name,
                    price,
                    id
                )
            }
        } catch (e: Exception) {
            stateError.value = e.message
        }
    }

    fun deleteFavorite(
        name: String,
        price: String
    ) {
        try {
            scope.launch {
                baseFavoriteManager.deleteItem(
                    name,
                    price
                )
            }
        } catch (e: Exception) {
            stateError.value = e.message
        }
    }
}