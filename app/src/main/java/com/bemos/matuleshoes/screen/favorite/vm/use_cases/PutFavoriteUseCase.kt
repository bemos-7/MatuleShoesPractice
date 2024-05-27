package com.bemos.matuleshoes.screen.favorite.vm.use_cases

import com.bemos.matuleshoes.data.supabase.card.BaseFavoriteManager

class PutFavoriteUseCase(
    private val baseFavoriteManager: BaseFavoriteManager
) {

    suspend operator fun invoke(
        image: String,
        name: String,
        price: String,
        id: String
    ) {
        baseFavoriteManager.putItem(
            image,
            name,
            price,
            id
        )
    }

}