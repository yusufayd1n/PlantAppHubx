package com.example.plantapphubx.presantation.onboarding.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plantapphubx.databinding.FragmentTakeAPhotoBinding
import com.example.plantapphubx.presantation.onboarding.OnBoardingFragment

class TakeAPhotoFragment : Fragment() {
    private var _binding: FragmentTakeAPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTakeAPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbContinue.setOnClickListener {
            (requireParentFragment() as? OnBoardingFragment)?.goToPage(1)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}