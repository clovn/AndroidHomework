package com.example.recycler_view.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.recycler_view.model.MultiHoldersData
import com.example.recycler_view.model.TextImageHolderData


class MultiHoldersDiffCallback(
    private val oldList: List<MultiHoldersData>,
    private val newList: List<MultiHoldersData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if(oldList[oldItemPosition] is TextImageHolderData && newList[newItemPosition] is TextImageHolderData){
            oldList[oldItemPosition] == newList[newItemPosition]
        } else {
            false
        }
    }
}
