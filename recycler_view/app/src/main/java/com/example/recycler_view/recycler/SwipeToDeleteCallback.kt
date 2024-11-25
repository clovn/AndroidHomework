package com.example.recycler_view.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.holder.ButtonViewHolder

var isSwipe = true

class SwipeToDeleteCallback(
    private val onSwipedAction : (Int) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if(viewHolder is ButtonViewHolder){
            0
        } else {
            super.getMovementFlags(recyclerView, viewHolder)
        }
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return isSwipe
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        onSwipedAction(position)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.66f
    }
}