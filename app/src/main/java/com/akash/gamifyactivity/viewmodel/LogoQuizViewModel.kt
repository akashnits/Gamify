package com.akash.gamifyactivity.viewmodel

import android.content.res.AssetManager
import androidx.lifecycle.*
import com.akash.gamifyactivity.model.LogoItem
import com.akash.gamifyactivity.repository.LogoRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoQuizViewModel @Inject constructor(
    private val logoRepository: LogoRepository
) : ViewModel() {

    var logoItems: LiveData<List<LogoItem>?> = MutableLiveData()

    fun loadQuizFromAsset(assetManager: AssetManager?) {
        viewModelScope.launch {
            logoItems = logoRepository.loadQuizFromAsset(assetManager)
        }
    }

    fun getRandomQuestion(): LogoItem? {
        return logoItems.value?.randomOrNull()
    }


}