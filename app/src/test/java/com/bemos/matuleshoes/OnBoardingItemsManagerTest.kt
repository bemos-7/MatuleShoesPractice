package com.bemos.matuleshoes

import com.bemos.matuleshoes.screen.on_boarding.Item
import com.bemos.matuleshoes.screen.on_boarding.OnBoardingItemsManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OnBoardingItemsManagerTest {

    private var onBoardingItems = OnBoardingItemsManager()

    @Before
    fun beforeEach() {
        onBoardingItems = OnBoardingItemsManager()
    }

    @Test
    fun getImageAndText() {
        val expected = Item(R.drawable.shoes_second, "123", "234")

        onBoardingItems.add(expected)
        onBoardingItems.add(Item(R.drawable.shoes, "sdf", "qqwe"))
        onBoardingItems.add(Item(R.drawable.bg_onboarding, "sdfff2we", "1234erd"))
        onBoardingItems.add(Item(R.drawable.bg_onboarding, "s11dfff2we", "3311234erd"))

        val actual = onBoardingItems.getFirst()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getSize() {
        val expected = 3

        onBoardingItems.add(Item(R.drawable.shoes, "sdf", "qqwe"))
        onBoardingItems.add(Item(R.drawable.bg_onboarding, "sdfff2we", "1234erd"))
        onBoardingItems.add(Item(R.drawable.bg_onboarding, "s11dfff2we", "3311234erd"))
        onBoardingItems.add(Item(R.drawable.bg_onboarding, "s11d123fff2we", "3311234sdfgerd"))

        onBoardingItems.getFirst()

        val actual = onBoardingItems.size()
        Assert.assertEquals(expected, actual)
    }

}