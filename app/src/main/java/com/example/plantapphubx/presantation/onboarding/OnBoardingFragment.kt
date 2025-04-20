package com.example.plantapphubx.presantation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.plantapphubx.databinding.FragmentOnBoardingBinding
import com.example.plantapphubx.presantation.onboarding.adapter.OnboardingPagerAdapter

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OnboardingPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OnboardingPagerAdapter(this)
        binding.vpOnboarding.adapter = adapter
        binding.vpOnboarding.isUserInputEnabled = false
        binding.dotsIndicator.attachTo(binding.vpOnboarding)

        binding.vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.dotsIndicator.visibility =
                    if (position == 0 || position == 1) View.VISIBLE else View.GONE
            }
        })
    }

    fun goToPage(index: Int) {
        binding.vpOnboarding.currentItem = index
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}