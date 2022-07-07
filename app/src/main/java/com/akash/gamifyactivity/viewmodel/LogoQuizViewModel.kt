package com.akash.gamifyactivity.viewmodel

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.akash.gamifyactivity.model.LogoItem
import com.akash.gamifyactivity.repository.LogoRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogoQuizViewModel @Inject constructor(
    private val logoRepository: LogoRepository
) : ViewModel() {

    var logoItems: LiveData<List<LogoItem>?> = MutableLiveData()

    fun loadQuizFromAsset(assetManager: AssetManager?) {
        viewModelScope.launch {
            logoItems = logoRepository.loadQuizFromAsset(assetManager)
        }
    }

    fun getNextQuestion(): LogoItem? {
        return logoItems.value?.randomOrNull()
    }


}