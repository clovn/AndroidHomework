package com.example.recycler_view.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.databinding.ItemButtonBinding
import com.example.recycler_view.model.ButtonsHolderData

class ButtonViewHolder(
    private val binding : ItemButtonBinding,
    private val recyclerView : RecyclerView,
    private val ctx : Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(buttonsData: ButtonsHolderData){
        binding.apply {
            button1.text = buttonsData.btn1text
            button2.text = buttonsData.btn2text
            button3.text = buttonsData.btn3text

            button1.setOnClickListener{
                buttonsData.btn1Onclick(recyclerView, ctx)
            }
            button2.setOnClickListener {
                buttonsData.btn2Onclick(recyclerView, ctx)
            }
            button3.setOnClickListener {
                buttonsData.btn3Onclick(recyclerView, ctx)
            }
        }
    }
}