package com.bemos.matuleshoes.screen.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.card.BaseFavoriteManager
import com.bemos.matuleshoes.screen.favorite.vm.dataCase.FavoriteUseCases
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val favoriteUseCases: FavoriteUseCases
) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    fun putFavorite(
        image: String,
        name: String,
        price: String,
        id: String
    ) {
        scope.launch {
            try {
                favoriteUseCases.putFavoriteUseCase.invoke(
                    image,
                    name,
                    price,
                    id
                )
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }

    }

    fun deleteFavorite(
        name: String,
        price: String
    ) {
        scope.launch {
            try {
                favoriteUseCases.deleteFavoriteUseCase.invoke(
                    name,
                    price
                )
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }
    }
}