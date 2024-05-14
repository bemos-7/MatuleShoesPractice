package com.bemos.matuleshoes.screen.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bemos.matuleshoes.MainActivity
import com.bemos.matuleshoes.R
import com.bemos.matuleshoes.databinding.CategoryFragmentBinding
import com.bemos.matuleshoes.screen.category.vm.CategoryViewModel

class CategoryFragment : Fragment() {

    lateinit var binding: CategoryFragmentBinding

    lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val mainActivity = activity as MainActivity
            categoryViewModel = mainActivity.categoryViewModel

            categoryViewModel.state.observe(viewLifecycleOwner) {
                when(it.category) {
                    "All" -> allItem.setBackgroundResource(R.drawable.blue_conrner_rectangle)
                    "Outdoor" -> outdoorItem.setBackgroundResource(R.drawable.blue_conrner_rectangle)
                    "Tennis" -> tennisItem.setBackgroundResource(R.drawable.blue_conrner_rectangle)
                    "Running" -> runningItem.setBackgroundResource(R.drawable.blue_conrner_rectangle)
                }
                categoryTv.text = it.category
            }
        }
    }

}