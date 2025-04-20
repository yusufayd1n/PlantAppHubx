package com.example.plantapphubx.presantation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.plantapphubx.core.util.DialogUtil
import com.example.plantapphubx.core.util.NetworkUtils
import com.example.plantapphubx.core.util.applyGradientText

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private var questionsAdapter = QuestionsAdapter()
    private val categoriesAdapter = CategoriesAdapter()

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
        showInternetError()
    }

    private fun observeViewModel() {
        homeViewModel.apply {
            questionState.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResult.Success -> {
                        binding.progressQuestions.visibility = View.GONE
                        questionsAdapter.submitList(result.data)
                    }

                    is ApiResult.Error -> {
                        binding.progressQuestions.visibility = View.GONE
                        DialogUtil.showAlert(requireContext(), result.exception.toString())
                    }

                    ApiResult.Loading -> {
                        binding.progressQuestions.visibility = View.VISIBLE
                    }
                }
            }

            categories.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResult.Success -> {
                        binding.progressCategories.visibility = View.GONE
                        lifecycleScope.launch {
                            categoriesAdapter.submitData(result.data)
                        }
                    }

                    is ApiResult.Error -> {
                        binding.progressCategories.visibility = View.GONE
                        DialogUtil.showAlert(requireContext(), result.exception.toString())
                    }

                    is ApiResult.Loading -> {
                        binding.progressCategories.visibility = View.VISIBLE
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

    private fun showInternetError() {
        if (!NetworkUtils.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), R.string.internet_error_text, Toast.LENGTH_LONG).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}