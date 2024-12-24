package com.example.viewpager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.AnswerHolder
import com.example.viewpager.Question
import com.example.viewpager.databinding.ItemAnswerBinding

class AnswersAdapter(
    private val question: Question,
) : RecyclerView.Adapter<AnswerHolder>() {

    private var selectedPosition = question.selectedAnswer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder = AnswerHolder(
        binding = ItemAnswerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = question.answers.size

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        holder.onBind(
            question.answers[position],
            position == selectedPosition
        ) {
            if (selectedPosition != position) {
                notifyItemChanged(selectedPosition)
                selectedPosition = holder.bindingAdapterPosition
                question.selectedAnswer = holder.bindingAdapterPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }
}