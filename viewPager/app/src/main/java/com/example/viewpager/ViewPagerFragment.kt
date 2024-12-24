package com.example.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.viewpager.adapter.AnswersAdapter
import com.example.viewpager.databinding.FragmentViewPagerBinding

const val QUESTION_ID = "QUESTION_ID"

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private val binding : FragmentViewPagerBinding by viewBinding()
    private var question : Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = DataRepository.getQuestion(it.getInt(QUESTION_ID))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
                question?.let {
                    questionTv.text = it.text
                    answersRv.adapter = AnswersAdapter(
                        question = it
                    )
                    answersRv.layoutManager = LinearLayoutManager(requireContext())
                }

        }
    }

    companion object{
        @JvmStatic
        fun newInstance(id: Int) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(QUESTION_ID, id)
                }
            }
    }
}