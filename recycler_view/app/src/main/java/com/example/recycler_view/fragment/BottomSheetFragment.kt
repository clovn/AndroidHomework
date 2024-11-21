package com.example.recycler_view.fragment

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.recycler_view.R
import com.example.recycler_view.databinding.BottomSheetBinding
import com.example.recycler_view.model.MultiHoldersData
import com.example.recycler_view.recycler.AdapterWithMultipleHolders
import com.example.recycler_view.repository.DataRepository
import com.example.recycler_view.repository.RandomRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.random.Random

class BottomSheetFragment(
    private val adapter : AdapterWithMultipleHolders
) : BottomSheetDialogFragment(R.layout.bottom_sheet) {

    private val binding : BottomSheetBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            addRndPosBtn.setOnClickListener {
                if(inputCount.editText?.text?.isEmpty() == true) return@setOnClickListener

                val count = inputCount.editText?.text.toString().toInt()
                val dataList = mutableListOf<MultiHoldersData>()
                dataList.addAll(adapter.getItems())

                for(i in 1..count){
                    dataList.add(Random.nextInt(1, dataList.size+ 1), RandomRepository.generateRandomItem())
                }

                adapter.updateItems(dataList)
                DataRepository.items = dataList

                dismiss()
            }

            addBtn.setOnClickListener {
                val dataList = mutableListOf<MultiHoldersData>()
                dataList.addAll(adapter.getItems())

                dataList.add(Random.nextInt(1, dataList.size+ 1), RandomRepository.generateRandomItem())

                adapter.updateItems(dataList)
                DataRepository.items = dataList

                dismiss()
            }

            removeRndPosBtn.setOnClickListener {
                if(inputCount.editText?.text?.isEmpty() == true) return@setOnClickListener

                val count = inputCount.editText?.text.toString().toInt()
                val dataList = mutableListOf<MultiHoldersData>()
                dataList.addAll(adapter.getItems())

                if(dataList.size - 1 <= count) dataList.retainAll(mutableListOf(dataList.first()))
                else {
                    for (i in 1..count) {
                        dataList.removeAt(Random.nextInt(1, dataList.size))
                    }
                }

                adapter.updateItems(dataList)
                DataRepository.items = dataList

                dismiss()
            }

            removeBtn.setOnClickListener {
                val dataList = mutableListOf<MultiHoldersData>()
                dataList.addAll(adapter.getItems())

                dataList.removeAt(Random.nextInt(1, dataList.size))

                adapter.updateItems(dataList)
                DataRepository.items = dataList

                dismiss()
            }
        }
    }

}