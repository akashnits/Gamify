package com.akash.gamifyactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModel
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModelFactory
import javax.inject.Inject

class LogoQuizActivity : AppCompatActivity() {

    @Inject lateinit var logoQuizViewModelFactory: LogoQuizViewModelFactory

    private val logoQuizViewModel: LogoQuizViewModel by viewModels {
        logoQuizViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as? LogoQuizApplication)?.appComponent?.injectActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_quiz)

        logoQuizViewModel.loadQuizFromAsset()
    }

    override fun onResume() {
        super.onResume()
        //setOnClickListener
    }
}