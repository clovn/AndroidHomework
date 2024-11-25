package com.example.recycler_view.utils

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.R
import com.example.recycler_view.model.LayoutManagerType
import com.example.recycler_view.recycler.AdapterWithMultipleHolders
import com.example.recycler_view.recycler.isSwipe

fun getValueInDp(value: Float, ctx: Context): Float {
    val metrics = ctx.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, metrics)
}

fun initLinearLayout(recycler: RecyclerView, ctx: Context){
    recycler.layoutManager =
        LinearLayoutManager(ctx, RecyclerView.VERTICAL, false)

    (recycler.adapter as AdapterWithMultipleHolders).setLayoutManagerType(LayoutManagerType.Linear)

    isSwipe = true
}

fun initGridLayout(recycler: RecyclerView, ctx: Context){
    recycler.layoutManager = GridLayoutManager(ctx, 3).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(recycler.adapter?.getItemViewType(position)) {
                    R.layout.card_text_image -> 1
                    R.layout.item_button -> 3
                    else -> 1
                }
            }
        }
    }

    (recycler.adapter as AdapterWithMultipleHolders).setLayoutManagerType(LayoutManagerType.Grid)

    isSwipe = false

}

fun initCustomLayout(recycler: RecyclerView, ctx: Context){
    recycler.layoutManager = GridLayoutManager(ctx, 2).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(recycler.adapter?.getItemViewType(position)) {
                    R.layout.card_text_image_custom ->
                        when(position % 4){
                            0, 1 -> 2
                            2, 3-> 1
                            else -> 1
                        }
                    R.layout.item_button -> 2
                    else -> 1
                }
            }
        }
    }

    (recycler.adapter as AdapterWithMultipleHolders).setLayoutManagerType(LayoutManagerType.Custom)

    isSwipe = false
}