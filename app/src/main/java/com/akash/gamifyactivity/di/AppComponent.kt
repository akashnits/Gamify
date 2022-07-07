package com.akash.gamifyactivity.di

import android.content.Context
import com.akash.gamifyactivity.LogoQuizActivity
import com.akash.gamifyactivity.ui.LogoQuizFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    @Component.Factory
    interface Factory  {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun injectFragment(logoQuizFragment: LogoQuizFragment)
    fun injectActivity(logoQuizActivity: LogoQuizActivity)
}