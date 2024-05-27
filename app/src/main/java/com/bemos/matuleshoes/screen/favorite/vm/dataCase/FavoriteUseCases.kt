package com.bemos.matuleshoes.screen.favorite.vm.dataCase

import com.bemos.matuleshoes.screen.favorite.vm.use_cases.DeleteFavoriteUseCase
import com.bemos.matuleshoes.screen.favorite.vm.use_cases.PutFavoriteUseCase

data class FavoriteUseCases(
    val putFavoriteUseCase: PutFavoriteUseCase,
    val deleteFavoriteUseCase: DeleteFavoriteUseCase
)