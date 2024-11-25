package com.example.recycler_view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.recycler_view.R
import com.example.recycler_view.databinding.FragmentItemInfoBinding
import com.example.recycler_view.fragment.ScreenTags.ID
import com.example.recycler_view.model.TextImageHolderData
import com.example.recycler_view.repository.DataRepository

class ItemInfoFragment : Fragment(R.layout.fragment_item_info) {

    private var id: Int? = null
    private val binding: FragmentItemInfoBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = DataRepository.items.find {
            it.id == id
        } as TextImageHolderData

        binding.apply {
            Glide.with(requireContext())
                .load(data.imageUrl)
                .error(R.mipmap.ic_launcher)
                .fitCenter()
                .into(detailImage)

            detailTitle.text = data.text
            detailDescription.text = data.desc
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            ItemInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID, id)
                }
            }
    }
}