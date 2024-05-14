package com.bemos.matuleshoes.screen.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bemos.matuleshoes.data.supabase.card.Product
import com.bemos.matuleshoes.databinding.FavoriteListitemBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class FavoriteAdapter {

    private val favoriteDelegate = adapterDelegateViewBinding<Product, Product, FavoriteListitemBinding>(
        viewBinding = { layoutInflater, parent ->
            FavoriteListitemBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.image.load(item.image)
            binding.nameTv.text = item.name
            binding.priceTv.text = item.price
        }
    }

    val adapter = ListDelegationAdapter(favoriteDelegate)

    fun updateList(items: List<Product>) {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }
}