package com.akash.gamifyactivity.viewmodel

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.akash.gamifyactivity.model.LogoItem
import com.akash.gamifyactivity.repository.LogoRepository
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoQuizViewModel @Inject constructor(
    private val logoRepository: LogoRepository,
    private val assetManager: AssetManager?
) : ViewModel() {

    var logoItems: LiveData<List<LogoItem>?> = MutableLiveData()
    var currentLogoItem: LogoItem? = null
    var timeElapsed: MutableLiveData<String?> = MutableLiveData()
    var countDownTimer: CountDownTimer? = null

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

    fun startTimer() {
        countDownTimer=  object: CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                var counter = millisUntilFinished / 1000
                timeElapsed.value = counter.toString()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timeElapsed.value = "0"
            }
        }
        (countDownTimer as CountDownTimer).start()
    }

    fun stopTimer(){
        (countDownTimer as CountDownTimer).cancel()
    }

}