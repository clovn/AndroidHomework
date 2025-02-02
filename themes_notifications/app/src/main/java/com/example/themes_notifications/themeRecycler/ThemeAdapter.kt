package com.example.themes_notifications.themeRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.themes_notifications.databinding.ItemThemeBinding
import com.example.themes_notifications.model.Theme

class ThemeAdapter(
    private val list: List<Theme>,
    private val onclick: (Int) -> Unit
) : Adapter<ThemeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeHolder = ThemeHolder(
        binding = ItemThemeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onclick = onclick
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ThemeHolder, position: Int) {
        holder.bind(list[position])
    }
}