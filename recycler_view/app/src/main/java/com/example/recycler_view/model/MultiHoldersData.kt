package com.example.recycler_view.model

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

sealed class MultiHoldersData(
    open val id : Int,
)

class TextImageHolderData(
    override val id : Int,
    val text : String,
    val desc : String,
    val imageUrl : String,
) : MultiHoldersData(id) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextImageHolderData

        if (text != other.text) return false
        if (desc != other.desc) return false
        if (imageUrl != other.imageUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + imageUrl.hashCode()
        return result
    }
}


class ButtonsHolderData(
    override val id : Int,
    val btn1text : String,
    val btn2text : String,
    val btn3text : String,
    val btn1Onclick : (RecyclerView, Context) -> Unit,
    val btn2Onclick : (RecyclerView, Context) -> Unit,
    val btn3Onclick : (RecyclerView, Context) -> Unit
) : MultiHoldersData(id)