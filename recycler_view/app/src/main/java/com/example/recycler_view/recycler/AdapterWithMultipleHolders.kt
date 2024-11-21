package com.example.recycler_view.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.recycler_view.R
import com.example.recycler_view.databinding.CardTextImageBinding
import com.example.recycler_view.databinding.CardTextImageCustomBinding
import com.example.recycler_view.databinding.ItemButtonBinding
import com.example.recycler_view.databinding.ItemTextImageBinding
import com.example.recycler_view.holder.ButtonViewHolder
import com.example.recycler_view.holder.TextImageViewHolder
import com.example.recycler_view.holder.TextImageViewHolderGrid
import com.example.recycler_view.holder.TextImageViewHolderCustomGrid
import com.example.recycler_view.model.ButtonsHolderData
import com.example.recycler_view.model.LayoutManagerType
import com.example.recycler_view.model.MultiHoldersData
import com.example.recycler_view.model.TextImageHolderData


class AdapterWithMultipleHolders(
    private var layoutManagerType: LayoutManagerType = LayoutManagerType.Linear,
    private val requestManager : RequestManager,
    dataList : MutableList<MultiHoldersData>,
    private val onClick : (Int) -> Unit,
    private val onLongClick : (Int) -> Unit,
    private val recyclerView : RecyclerView,
    private val ctx : Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<MultiHoldersData>()

    init {
        items.addAll(dataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_text_image -> {
                TextImageViewHolder(
                    binding = ItemTextImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    requestManager = requestManager,
                    onClick = onClick,
                )
            }

            R.layout.card_text_image -> {
                TextImageViewHolderGrid(
                    binding = CardTextImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    requestManager = requestManager,
                    onClick = onClick,
                    onLongClick = onLongClick,
                )
            }

            R.layout.card_text_image_custom -> {
                TextImageViewHolderCustomGrid(
                    binding = CardTextImageCustomBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    requestManager = requestManager,
                    onClick = onClick
                )
            }

            R.layout.item_button -> {
                ButtonViewHolder(
                    binding = ItemButtonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    recyclerView,
                    ctx
                )
            }

            else -> throw IllegalStateException("Unknown holder")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position]) {
            is TextImageHolderData -> {
                when (layoutManagerType) {
                    LayoutManagerType.Linear -> (holder as? TextImageViewHolder)?.bindItem(itemData = items[position] as TextImageHolderData)
                    LayoutManagerType.Grid -> (holder as? TextImageViewHolderGrid)?.bindItem(itemData = items[position] as TextImageHolderData)
                    LayoutManagerType.Custom -> (holder as? TextImageViewHolderCustomGrid)?.bindItem(itemData = items[position] as TextImageHolderData, position, recyclerView.height - 64)
                }
            }

            is ButtonsHolderData -> {
                (holder as? ButtonViewHolder)?.bindItem(buttonsData = items[position] as ButtonsHolderData)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            is TextImageHolderData -> {
                when(layoutManagerType){
                    LayoutManagerType.Linear -> R.layout.item_text_image
                    LayoutManagerType.Grid -> R.layout.card_text_image
                    LayoutManagerType.Custom -> R.layout.card_text_image_custom
                }
            }

            is ButtonsHolderData -> {
                R.layout.item_button
            }
        }
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItems(newItems: MutableList<MultiHoldersData>) {
        val diffCallback = MultiHoldersDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)

    }

    fun setLayoutManagerType(type: LayoutManagerType) {
        layoutManagerType = type
    }

    fun getItems() : MutableList<MultiHoldersData> = items
}