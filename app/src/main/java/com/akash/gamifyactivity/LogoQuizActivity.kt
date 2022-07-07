package com.akash.gamifyactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.akash.gamifyactivity.ui.WelcomeScreenFragment
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModel
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModelFactory
import javax.inject.Inject

class LogoQuizActivity : AppCompatActivity() {

    @Inject
    lateinit var logoQuizViewModelFactory: LogoQuizViewModelFactory

    val logoQuizViewModel: LogoQuizViewModel by viewModels {
        logoQuizViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as LogoQuizApplication).appComponent.injectActivity(this)
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