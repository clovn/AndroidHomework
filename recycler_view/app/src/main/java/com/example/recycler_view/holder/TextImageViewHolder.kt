package com.example.recycler_view.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.recycler_view.R
import com.example.recycler_view.databinding.ItemTextImageBinding
import com.example.recycler_view.model.TextImageHolderData

class TextImageViewHolder(
    private val binding : ItemTextImageBinding,
    private val requestManager : RequestManager,
    private val onClick : (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {


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