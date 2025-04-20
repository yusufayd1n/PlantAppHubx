package com.example.plantapphubx.presantation.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapphubx.databinding.ItemPremiumBinding
import com.example.plantapphubx.presantation.onboarding.model.PremiumItemModel

class PremiumAdapter(
    private val items: List<PremiumItemModel>
) : RecyclerView.Adapter<PremiumAdapter.PremiumViewHolder>() {

    inner class PremiumViewHolder(val binding: ItemPremiumBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPremiumBinding.inflate(inflater, parent, false)
        return PremiumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PremiumViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvTittle.text = item.title
            tvSubTittle.text = item.subTitle
            ivIcon.setImageResource(item.image)
        }
    }

    override fun getItemCount(): Int = items.size
}