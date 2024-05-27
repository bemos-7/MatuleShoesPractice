package com.bemos.matuleshoes.screen.on_boarding

class OnBoardingItemsManager {

    private val onBoardingItems = ArrayDeque<Item>()

    fun isEmpty(): Boolean {
        return onBoardingItems.isEmpty()
    }

    fun size(): Int {
        return onBoardingItems.size
    }

    fun getFirst(): Item {
        return onBoardingItems.removeFirst()
    }

    fun add(item: Item) {
        onBoardingItems.add(item)
    }

}
data class Item(
    val image: Int,
    val title: String,
    val description: String,
    val pager: Int
)