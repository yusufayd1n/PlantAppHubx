package com.example.plantapphubx.presantation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private var questionsAdapter = QuestionsAdapter()
    private val categoriesAdapter = CategoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO
        homeViewModel.getQuestionsFromApi()
        homeViewModel.fetchCategories()
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
        observeCategories()
        setupQuestionsRecyclerView()
        setupRecyclerView()
    }

    private fun observeViewModel() {
        homeViewModel.questionState.observe(viewLifecycleOwner) { result ->
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
    }

    private fun setupRecyclerView() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategories() {
        homeViewModel.categories.observe(viewLifecycleOwner) { result ->
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

    private fun setupQuestionsRecyclerView() {
        binding.rvQuestions.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = questionsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}