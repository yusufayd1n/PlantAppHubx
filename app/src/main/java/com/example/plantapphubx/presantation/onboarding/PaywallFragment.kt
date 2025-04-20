package com.example.plantapphubx.presantation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapphubx.R
import com.example.plantapphubx.data.PreferenceManager
import com.example.plantapphubx.databinding.FragmentPaywallBinding
import com.example.plantapphubx.presantation.onboarding.adapter.PremiumAdapter
import com.example.plantapphubx.presantation.onboarding.model.PremiumItemModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaywallFragment : Fragment() {
    private var _binding: FragmentPaywallBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferencesHelper: PreferenceManager

    private val premiumItems = listOf(
        PremiumItemModel("Unlimited", "Plant Identify", R.drawable.premium_1),
        PremiumItemModel("Faster", "Process", R.drawable.premium_2),
        PremiumItemModel("Better", "Better Information", R.drawable.premium_2)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaywallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbTryPremium.setOnClickListener {
            sharedPreferencesHelper.setMyBoolean(true)
            findNavController().navigate(R.id.nav_home)
        }
        setUpRecyclerView()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        binding.rvPremium.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPremium.adapter = PremiumAdapter(premiumItems)
    }

}