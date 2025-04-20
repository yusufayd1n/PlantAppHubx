package com.example.plantapphubx.presantation.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.plantapphubx.presantation.onboarding.pages.GuideFragment
import com.example.plantapphubx.presantation.onboarding.PaywallFragment
import com.example.plantapphubx.presantation.onboarding.pages.TakeAPhotoFragment

class OnboardingPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TakeAPhotoFragment()
            1 ->  GuideFragment()
            else -> PaywallFragment()
        }
    }
}
