package com.bemos.matuleshoes.screen.favorite.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemos.matuleshoes.data.supabase.card.Product
import com.bemos.matuleshoes.screen.favorite.vm.use_cases.FavoriteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO)

    val stateError = MutableLiveData<String>()

    val state = MutableLiveData<List<Product>>()

    fun getFavorite(
        id: String
    ) {
        scope.launch {
            try {
                state.postValue(favoriteUseCase.invoke(id))
            } catch (e: Exception) {
                stateError.postValue(e.message)
            }
        }

    }

}