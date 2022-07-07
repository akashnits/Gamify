package com.akash.gamifyactivity

import android.app.Application
import com.akash.gamifyactivity.di.AppComponent
import com.akash.gamifyactivity.di.DaggerAppComponent

class LogoQuizApplication : Application() {
    val appComponent : AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent() : AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}