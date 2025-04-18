package com.example.plantapphubx.presantation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import com.example.plantapphubx.databinding.ItemCategoriesBinding

class CategoriesAdapter :
    PagingDataAdapter<CategoriesResponse, CategoriesAdapter.CategoryViewHolder>(DiffCallback()) {

    inner class CategoryViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoriesResponse) {
            binding.category = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class DiffCallback : DiffUtil.ItemCallback<CategoriesResponse>() {
        override fun areItemsTheSame(oldItem: CategoriesResponse, newItem: CategoriesResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoriesResponse, newItem: CategoriesResponse): Boolean {
            return oldItem == newItem
        }
    }
}
