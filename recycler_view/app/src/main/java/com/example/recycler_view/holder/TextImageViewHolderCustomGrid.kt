package com.example.recycler_view.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.recycler_view.R
import com.example.recycler_view.databinding.CardTextImageCustomBinding
import com.example.recycler_view.model.TextImageHolderData

class TextImageViewHolderCustomGrid (
    private val binding: CardTextImageCustomBinding,
    private val requestManager : RequestManager,
    private val onClick : (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(itemData: TextImageHolderData, position : Int, recyclerHeight: Int){
        binding.apply {
            when(position % 4){
                0, 1 -> {
                    root.layoutParams.height = recyclerHeight / 4
                }
                2, 3 -> {
                    root.layoutParams.height = recyclerHeight / 2
                }
            }

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