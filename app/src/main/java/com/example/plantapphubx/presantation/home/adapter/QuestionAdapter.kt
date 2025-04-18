package com.example.plantapphubx.presantation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapphubx.data.remote.dto.QuestionsResponse
import com.example.plantapphubx.databinding.ItemQuestionBinding

class QuestionsAdapter :
    ListAdapter<QuestionsResponse, QuestionsAdapter.QuestionsViewHolder>(QuestionsDiffCallback()) {

    inner class QuestionsViewHolder(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuestionsResponse) {
            binding.question = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemQuestionBinding.inflate(layoutInflater, parent, false)
        return QuestionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        val question = getItem(position)
        holder.bind(question)
    }
}

class QuestionsDiffCallback : DiffUtil.ItemCallback<QuestionsResponse>() {
    override fun areItemsTheSame(oldItem: QuestionsResponse, newItem: QuestionsResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: QuestionsResponse,
        newItem: QuestionsResponse
    ): Boolean {
        return oldItem == newItem
    }
}
