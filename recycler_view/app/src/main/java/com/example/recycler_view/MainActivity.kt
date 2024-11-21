package com.example.recycler_view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.recycler_view.fragment.RecyclerFragment
import com.example.recycler_view.model.LayoutManagerType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState == null){
            openFragment(RecyclerFragment.newInstance(LayoutManagerType.Linear), false)
        }
    }

    fun openFragment(fragment: Fragment, addToBackStack : Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction().replace(R.id.container , fragment)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
    }
}