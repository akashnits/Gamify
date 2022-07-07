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
    private val logoRepository: LogoRepository,
    private val assetManager: AssetManager?
) : ViewModel() {

    var logoItems: LiveData<List<LogoItem>?> = MutableLiveData()
    var currentLogoItem : LogoItem? = null

    init {
        loadQuizFromAsset(assetManager)
    }

    fun loadQuizFromAsset(assetManager: AssetManager?) {
        viewModelScope.launch {
            logoItems = logoRepository.loadQuizFromAsset(assetManager)
        }
    }

    fun getRandomQuestion(): LogoItem? {
        //get a random question
        val logoItem = logoItems.value?.filter { !it.asked }?.randomOrNull()
        currentLogoItem = logoItem
        currentLogoItem?.asked = true
        return logoItem
    }

    fun isValidAnswer(ans: String): Boolean {
        return ans.equals(currentLogoItem?.name, ignoreCase = true)
    }

}