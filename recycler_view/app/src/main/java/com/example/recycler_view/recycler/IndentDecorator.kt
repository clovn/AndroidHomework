package com.example.recycler_view.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.holder.TextImageViewHolder

class IndentDecorator(
    private val marginHorizontal : Int,
    private val marginVertical: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val holder = parent.getChildViewHolder(view)
        if (holder is TextImageViewHolder) {
            outRect.apply {
                left = marginHorizontal
                right = marginHorizontal
                top = marginVertical
                bottom = marginVertical
            }
        }
    }
}