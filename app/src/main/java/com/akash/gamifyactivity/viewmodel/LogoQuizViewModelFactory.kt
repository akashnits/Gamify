package com.akash.gamifyactivity.viewmodel


import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.gamifyactivity.repository.LogoRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LogoQuizViewModelFactory @Inject constructor(
    private val logoRepository: LogoRepository,
    private val assetManager: AssetManager
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LogoQuizViewModel(logoRepository, assetManager) as T
    }
}