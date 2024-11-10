package ru.kpfu.itis.activity_fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        openFragment(FirstFragment(), false)

    }

    fun openFragment(fragment: Fragment, addToBackStack : Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction().replace(R.id.container , fragment)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
    }
}