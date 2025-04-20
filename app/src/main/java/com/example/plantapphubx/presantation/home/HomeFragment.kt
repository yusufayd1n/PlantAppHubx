package com.example.plantapphubx.presantation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.databinding.FragmentHomeBinding
import com.example.plantapphubx.presantation.home.adapter.CategoriesAdapter
import com.example.plantapphubx.presantation.home.adapter.QuestionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.plantapphubx.R
import com.example.plantapphubx.core.util.applyGradientText

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private var questionsAdapter = QuestionsAdapter()
    private val categoriesAdapter = CategoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupRecyclerViews()
        paintTextViews()
        handleBackPress()
    }

    private fun observeViewModel() {
        homeViewModel.apply {
            questionState.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResult.Error -> {
                        //TODO
                        //Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }

                    ApiResult.Loading -> {
                        //TODO
                        //binding.progressBar.visibility = View.VISIBLE
                    }

                    is ApiResult.Success -> {
                        //binding.progressBar.visibility = View.GONE
                        questionsAdapter.submitList(result.data)
                    }
                }
            }

            categories.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResult.Success -> {
                        lifecycleScope.launch {
                            categoriesAdapter.submitData(result.data)
                        }
                    }

                    is ApiResult.Error -> {
                        //TODO
                        //Toast.makeText(requireContext(), result.message ?: "Error", Toast.LENGTH_SHORT).show()
                    }

                    is ApiResult.Loading -> {
                        //TODO
                        // loading
                    }
                }
            }
        }

    }

    private fun setupRecyclerViews() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoriesAdapter
        }

        binding.rvQuestions.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = questionsAdapter
        }
    }

    private fun paintTextViews() {
        binding.tvPremiumTitle.applyGradientText(
            resources.getColor(R.color.premium_title_start_color),
            resources.getColor(R.color.premium_title_end_color)
        )
        binding.tvPremiumSubTitle.applyGradientText(
            resources.getColor(R.color.premium_sub_title_start_color),
            resources.getColor(R.color.premium_sub_title_end_color)
        )
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}