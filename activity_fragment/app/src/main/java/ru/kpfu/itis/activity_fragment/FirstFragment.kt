package ru.kpfu.itis.activity_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.activity_fragment.databinding.FragmentFirstBinding


class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding : FragmentFirstBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val mainActivity = activity as MainActivity

            buttonToSecondScreen.setOnClickListener{
                mainActivity.openFragment(SecondFragment.newInstance(textInput.editText?.text.toString()))
            }

            buttonToThirdScreen.setOnClickListener{
                val text = textInput.editText?.text.toString()
                mainActivity.openFragment(SecondFragment.newInstance(text))
                mainActivity.openFragment(ThirdFragment.newInstance(text))
            }
        }
    }
}