package com.example.plantapphubx.presantation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.plantapphubx.R
import com.example.plantapphubx.data.PreferenceManager
import com.example.plantapphubx.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferencesHelper: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageOnBoardingFlow()
        binding.mbGetStartedButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_onBoardingFragment)
        }
    }

    private fun manageOnBoardingFlow() {
        if (sharedPreferencesHelper.getMyBoolean()) {
            findNavController().navigate(R.id.nav_home)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}