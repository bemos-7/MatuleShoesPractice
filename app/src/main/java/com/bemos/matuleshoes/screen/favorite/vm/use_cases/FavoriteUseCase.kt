package com.bemos.matuleshoes.screen.favorite.vm.use_cases

import com.bemos.matuleshoes.data.supabase.card.BaseFavoriteManager
import com.bemos.matuleshoes.data.supabase.card.Product

class FavoriteUseCase(
    private val baseFavoriteManager: BaseFavoriteManager
) {

    suspend operator fun invoke(
        id: String
    ) : List<Product> {
        val products = baseFavoriteManager.getItems(id)
        return products
    }

}