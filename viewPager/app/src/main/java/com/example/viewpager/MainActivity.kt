package com.example.viewpager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.viewpager.adapter.TestPagerAdapter
import com.example.viewpager.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()

        updateButtonState()
    }



    private fun initViews() {
        binding.apply {
            testVp.adapter = TestPagerAdapter(lifecycle, supportFragmentManager, DataRepository.questions.map {
                ViewPagerFragment.newInstance(it.id)
            })

            testVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    numberTv.text = "${position + 1} / ${DataRepository.questions.size}"
                    updateButtonState()
                }
            })

            nextBtn.setOnClickListener {
                if (testVp.currentItem < (testVp.adapter?.itemCount?.minus(1) ?: 0)) {
                    testVp.currentItem += 1
                } else {
                    if(DataRepository.questions.filter { it.selectedAnswer > 0 }.isNotEmpty()){
                        Snackbar.make(testVp,
                            getString(R.string.results_save), Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(testVp,
                            getString(R.string.not_all_answers), Snackbar.LENGTH_SHORT)
                            .show()
                    }

                }
            }

            backBtn.setOnClickListener {
                if (testVp.currentItem > 0) {
                    testVp.currentItem -= 1
                }
            }

        }
    }

    private fun updateButtonState() {
        binding.apply {
            backBtn.isEnabled = testVp.currentItem > 0

            if(testVp.currentItem < (testVp.adapter?.itemCount?.minus(1) ?: 0)) {
                nextBtn.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.primary))
                nextBtn.text = resources.getString(R.string.next)
            }

            else {
                nextBtn.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.light_green))
                nextBtn.text = resources.getString(R.string.finish)
            }
        }
    }
}