package com.example.plantapphubx.presantation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.databinding.FragmentHomeBinding
import com.example.plantapphubx.presantation.home.adapter.QuestionsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var questionsAdapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getQuestionsFromApi()
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
        setupQuestionsRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel.questionState.observe(viewLifecycleOwner) { result->
            when(result) {
                is ApiResult.Error -> {
                    // Hata mesajını göster
                    //Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                ApiResult.Loading -> {
                    // Progress göster
                    //binding.progressBar.visibility = View.VISIBLE
                }
                is ApiResult.Success -> {
                    // Tipi artık biliyoruz: result.data: List<QuestionsResponse>
                    //binding.progressBar.visibility = View.GONE
                    questionsAdapter.submitList(result.data)
                }
            }
        }
    }

    private fun setupQuestionsRecyclerView() {
        questionsAdapter = QuestionsAdapter()

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