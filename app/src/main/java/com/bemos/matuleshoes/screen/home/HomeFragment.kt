package com.bemos.matuleshoes.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bemos.matuleshoes.App
import com.bemos.matuleshoes.MainActivity
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.data.email.EmailManager
import com.bemos.matuleshoes.data.email.MainActivityViewModel
import com.bemos.matuleshoes.data.id_manager.IdManager
import com.bemos.matuleshoes.databinding.HomeFragmentBinding
import com.bemos.matuleshoes.screen.category.CategoryFragment
import com.bemos.matuleshoes.screen.category.vm.CategoryViewModel
import com.bemos.matuleshoes.screen.category.vm.Item
import com.bemos.matuleshoes.screen.favorite.vm.dataCase.FavoriteUseCases
import com.bemos.matuleshoes.screen.favorite.vm.use_cases.DeleteFavoriteUseCase
import com.bemos.matuleshoes.screen.favorite.vm.use_cases.PutFavoriteUseCase
import com.bemos.matuleshoes.screen.home.vm.HomeSecondViewModel
import com.bemos.matuleshoes.screen.home.vm.HomeViewModel
import com.bemos.matuleshoes.screen.profile_screen.use_case.ProfileUseCase
import com.bemos.matuleshoes.screen.side_menu.SideMenuFragment

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding()

    lateinit var categoryViewModel: CategoryViewModel

    lateinit var mainActivityViewModel: MainActivityViewModel

    val homeViewModel = HomeViewModel(
        FavoriteUseCases(
            PutFavoriteUseCase(App.instance.baseFavoriteManager),
            DeleteFavoriteUseCase(App.instance.baseFavoriteManager)
        )
    )
    val homeSecondViewModel = HomeSecondViewModel(ProfileUseCase(App.instance.baseProfileManager))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val mainActivity = activity as MainActivity
            mainActivity.binding.containerBottomNav.visibility = View.VISIBLE
            categoryViewModel = mainActivity.categoryViewModel

            mainActivityViewModel = mainActivity.mainActivityViewModel

            val emailManager = EmailManager(requireContext())
            val idManager = IdManager(requireContext())

            homeSecondViewModel.getProfile(emailManager.get())

            homeSecondViewModel.state.observe(viewLifecycleOwner) {
                mainActivityViewModel.setId(it.id_!!)
                Log.d("keyItemId", it.id_!!)
            }

            Log.d("keyItemIdd", idManager.get())

            allItem.setOnClickListener {
                categoryViewModel.state.value = Item(
                    "All"
                )
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, CategoryFragment())
                    .commit()
            }

            outdoorItem.setOnClickListener {
                categoryViewModel.state.value = Item(
                    "Outdoor"
                )
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, CategoryFragment())
                    .commit()
            }

            tennisItem.setOnClickListener {
                categoryViewModel.state.value = Item(
                    "Tennis"
                )
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, CategoryFragment())
                    .commit()
            }

            runningItem.setOnClickListener {
                categoryViewModel.state.value = Item(
                    "Running"
                )
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, CategoryFragment())
                    .commit()
            }

            menuImg.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, SideMenuFragment())
                    .commit()
            }

            with(includedItem) {
                checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked == true) {
                        homeViewModel.putFavorite(
                            "https://w7.pngwing.com/pngs/335/960/png-transparent-air-force-1-sneakers-skate-shoe-nike-air-force-one-blue-white-outdoor-shoe.png",
                            nameTv.text.toString(),
                            priceTv.text.toString(),
                            idManager.get()
                        )
                        Toast.makeText(requireContext(), "checked", Toast.LENGTH_SHORT).show()
                    } else {
                        homeViewModel.deleteFavorite(
                            nameTv.text.toString(),
                            priceTv.text.toString()
                        )
                    }

                }
            }

        }
    }
}