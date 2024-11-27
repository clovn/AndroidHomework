package com.example.recycler_view.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.recycler_view.R
import com.example.recycler_view.databinding.CardTextImageBinding
import com.example.recycler_view.model.TextImageHolderData

class TextImageViewHolderGrid(
    private val binding: CardTextImageBinding,
    private val requestManager : RequestManager,
    private val onClick : (Int) -> Unit,
    private val onLongClick : (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {


    init {
        binding.root.setOnLongClickListener {
            onLongClick(adapterPosition)
            false
        }
    }

    fun bindItem(itemData: TextImageHolderData){
        binding.apply {

            text.text = itemData.text
            requestManager.load(itemData.imageUrl)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(image)

            root.setOnClickListener {
                onClick(itemData.id)
            }
        }
    }

}