package ru.kpfu.itis.activity_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kpfu.itis.activity_fragment.databinding.BottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var binding: BottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            button.setOnClickListener{
                val result = Bundle().apply {
                    putString("TEXT", binding!!.inputText.editText?.text.toString())
                }
                parentFragmentManager.setFragmentResult("result_text", result)
                dismiss()
            }

            inputText.editText?.addTextChangedListener{
                button.isEnabled = it?.isNotEmpty() == true
            }
        }
    }

}