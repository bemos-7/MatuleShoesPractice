package com.bemos.matuleshoes.screen.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.id_manager.IdManager
import com.bemos.matuleshoes.databinding.FavoriteFragmentBinding
import com.bemos.matuleshoes.screen.favorite.adapter.FavoriteAdapter
import com.bemos.matuleshoes.screen.favorite.vm.FavoriteViewModel
import com.bemos.matuleshoes.screen.favorite.vm.use_cases.FavoriteUseCase

class FavoriteFragment : Fragment(R.layout.favorite_fragment) {

    private val binding: FavoriteFragmentBinding by viewBinding()

    val favoriteViewModel = FavoriteViewModel(FavoriteUseCase(App.instance.baseFavoriteManager))

    val adapter = FavoriteAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            rcvItems.adapter = adapter.adapter

            val idManager = IdManager(requireContext())

            favoriteViewModel.getFavorite(idManager.get())

            favoriteViewModel.state.observe(viewLifecycleOwner) {
                adapter.updateList(it)
            }
        }
    }

}