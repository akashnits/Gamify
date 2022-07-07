package com.akash.gamifyactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.akash.gamifyactivity.ui.WelcomeScreenFragment

class LogoQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_quiz)

        if(savedInstanceState == null){
            val manager: FragmentManager = supportFragmentManager
            val fragment = WelcomeScreenFragment()
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.container, fragment).addToBackStack(null)
            transaction.commit()
        }
    }
}