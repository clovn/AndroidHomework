package com.example.recycler_view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.recycler_view.MainActivity
import com.example.recycler_view.R
import com.example.recycler_view.databinding.FragmentRecyclerBinding
import com.example.recycler_view.fragment.ScreenTags.LAYOUT_MANAGER_TYPE
import com.example.recycler_view.model.LayoutManagerType
import com.example.recycler_view.recycler.AdapterWithMultipleHolders
import com.example.recycler_view.recycler.IndentDecorator
import com.example.recycler_view.recycler.SwipeToDeleteCallback
import com.example.recycler_view.repository.DataRepository
import com.example.recycler_view.utils.getValueInDp
import com.example.recycler_view.utils.initCustomLayout
import com.example.recycler_view.utils.initGridLayout
import com.example.recycler_view.utils.initLinearLayout


class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    private val binding : FragmentRecyclerBinding by viewBinding()
    private var rvAdapter : AdapterWithMultipleHolders? = null
    private var layoutManagerType : LayoutManagerType? = null

    companion object {
        @JvmStatic
        fun newInstance(layoutManagerType: LayoutManagerType) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putInt(LAYOUT_MANAGER_TYPE, layoutManagerType.ordinal)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutManagerType = LayoutManagerType.entries[it.getInt(LAYOUT_MANAGER_TYPE)]
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

        when(layoutManagerType){
            LayoutManagerType.Linear -> initLinearLayout(binding.recycler, requireContext())
            LayoutManagerType.Grid -> initGridLayout(binding.recycler, requireContext())
            LayoutManagerType.Custom -> initCustomLayout(binding.recycler, requireContext())
            null -> throw RuntimeException("layoutManagerType is null")
        }

        initViews()
    }

    private fun initViews(){
        binding.apply {
            fab.setOnClickListener {
                BottomSheetFragment(binding.recycler.adapter as AdapterWithMultipleHolders)
                    .show(parentFragmentManager, "bottom_sheet")
            }
        }
    }

    private fun initRecycler() {
        binding.apply{
            rvAdapter = AdapterWithMultipleHolders(
                requestManager = Glide.with(requireContext()),
                dataList = DataRepository.items,
                onClick = {
                    (activity as MainActivity).openFragment(ItemInfoFragment.newInstance(it))
                },
                onLongClick = {
                    AlertDialog.Builder(requireContext())
                        .setMessage("Вы уверены, что хотите удалить этот элемент?")
                        .setPositiveButton("Удалить") { dialog, _ ->
                            rvAdapter?.removeItem(it)
                            dialog.dismiss()
                        }
                        .setNegativeButton("Отмена") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                },
                recyclerView = recycler,
                ctx = requireContext()
            )

            recycler.adapter = rvAdapter

            recycler.addItemDecoration(
                IndentDecorator(
                    getValueInDp(4f, requireContext()).toInt(),
                    getValueInDp(4f, requireContext()).toInt()
                )
            )

            ItemTouchHelper(SwipeToDeleteCallback { position ->
                (recycler.adapter as AdapterWithMultipleHolders).removeItem(position)
            }).attachToRecyclerView(recycler)
        }
    }
}