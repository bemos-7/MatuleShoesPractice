package com.bemos.matuleshoes.screen.category.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryViewModel : ViewModel() {

    val state = MutableLiveData<Item>()

}
data class Item(
    val category: String,
)