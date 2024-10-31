package ru.kpfu.itis.activity_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.activity_fragment.databinding.FragmentThirdBinding


private const val TEXT = "TEXT_PARAM"

class ThirdFragment : Fragment(R.layout.fragment_third) {
    private var text: String? = null
    private val binding : FragmentThirdBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(TEXT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(text?.isNotEmpty()!!){
            binding.textView.text = text
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(text: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(TEXT, text)
                }
            }
    }
}