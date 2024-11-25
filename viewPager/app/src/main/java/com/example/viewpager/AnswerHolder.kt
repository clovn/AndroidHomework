package com.example.viewpager

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.databinding.ItemAnswerBinding

class AnswerHolder(
    private val binding: ItemAnswerBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {

            ansRb.buttonTintList = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_checked)
                ),
                intArrayOf(
                    Color.GRAY,
                    Color.GREEN
                )
            )

            ansRb.setOnCheckedChangeListener { buttonView, isChecked ->
                card.setCardBackgroundColor(ContextCompat.getColor(buttonView.context, if(isChecked) R.color.right_answer else R.color.white))
            }
        }
    }

    fun onBind(item : String, isChecked : Boolean, onClick: () -> Unit){
        binding.apply {
            answerTv.text = item
            ansRb.isChecked = isChecked

            ansRb.setOnClickListener {
                onClick()
            }

            root.setOnClickListener {
                onClick()
            }
        }
    }
}