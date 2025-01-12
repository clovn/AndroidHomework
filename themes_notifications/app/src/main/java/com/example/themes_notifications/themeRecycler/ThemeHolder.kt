package com.example.themes_notifications.themeRecycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.themes_notifications.databinding.ItemThemeBinding
import com.example.themes_notifications.model.Theme

class ThemeHolder(
    private val binding: ItemThemeBinding,
    private val onclick: (Int) -> Unit
) : ViewHolder(binding.root) {

    fun bind(theme: Theme){
        binding.apply {
            square.setBackgroundColor(theme.color)
            square.setOnClickListener {
                onclick(theme.id)
            }
        }
    }
}