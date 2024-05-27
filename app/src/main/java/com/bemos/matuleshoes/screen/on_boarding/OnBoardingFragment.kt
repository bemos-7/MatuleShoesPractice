package com.bemos.matuleshoes.screen.on_boarding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.databinding.OnBoardingFragmentBinding
import com.bemos.matuleshoes.screen.user.sign_in.SignInFragment

class OnBoardingFragment : Fragment(R.layout.on_boarding_fragment) {

    private val binding: OnBoardingFragmentBinding by viewBinding()

    var onBoardingItemsManager = OnBoardingItemsManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

            onBoardingItemsManager.add(Item(
                R.drawable.firstboard,
                "",
                "",
                R.drawable.firstboard
            ))

            onBoardingItemsManager.add(Item(
                R.drawable.secondboard,
                "Начнем путешествие",
                "Умная, великолепная и модная коллекция Изучите сейчас",
                R.drawable.pager_second
            ))

            onBoardingItemsManager.add(Item(
                R.drawable.thirdboard,
                "У вас есть сила, чтобы",
                "В вашей комнате много красивых и привлекательных растений",
                R.drawable.pager_thirs
            ))

            val firstItem = onBoardingItemsManager.getFirst()

            shoesImage.setImageResource(firstItem.image)
            title.text = firstItem.title
            desc.text = firstItem.description
            pagerImageSecond.setImageResource(firstItem.pager)

            startBtn.setOnClickListener {
                pagerImage.visibility = View.GONE
                titleDescContainer.visibility = View.VISIBLE

                if (onBoardingItemsManager.isEmpty() == false) {
                    val secondItem = onBoardingItemsManager.getFirst()
                    Log.d("sizeOnBoard", onBoardingItemsManager.size().toString())
                    shoesImage.setImageResource(secondItem.image)
                    title.text = secondItem.title
                    desc.text = secondItem.description
                    binding.startBtn.text = "Далее"
                    pagerImageSecond.setImageResource(secondItem.pager)

                    if (onBoardingItemsManager.size() == 0) {

                        nextBtn.visibility = View.VISIBLE
                        startBtn.visibility = View.GONE
                    }
                } else {
                    nextBtn.visibility = View.VISIBLE
                    startBtn.visibility = View.GONE
                }
            }

            nextBtn.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, SignInFragment())
                    .commit()
            }

        }

    }

}