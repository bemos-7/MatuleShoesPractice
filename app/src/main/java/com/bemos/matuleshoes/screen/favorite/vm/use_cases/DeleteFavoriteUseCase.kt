package com.bemos.matuleshoes.screen.favorite.vm.use_cases

import com.bemos.matuleshoes.data.supabase.card.BaseFavoriteManager
import okhttp3.internal.ignoreIoExceptions

class DeleteFavoriteUseCase(
    private val baseFavoriteManager: BaseFavoriteManager
) {

    suspend operator fun invoke(
        name: String,
        price: String
    ) {
        baseFavoriteManager.deleteItem(
            name, price
        )
    }

}