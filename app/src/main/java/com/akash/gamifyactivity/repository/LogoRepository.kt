package com.akash.gamifyactivity.repository

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.akash.gamifyactivity.LOGO_FILE
import com.akash.gamifyactivity.model.LogoItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoRepository @Inject constructor() {

    // load assets and returns list of logo items
    suspend fun loadQuizFromAsset(assetManager: AssetManager?): LiveData<List<LogoItem>?> {

        return liveData(Dispatchers.IO) {
            emit(assetManager?.open(LOGO_FILE).use { inputStream ->
                JsonReader(inputStream?.reader()).use { jsonReader ->
                    val modelType = object : TypeToken<ArrayList<LogoItem>>() {}.type
                    val modelList: List<LogoItem> = Gson().fromJson(jsonReader, modelType)
                    modelList
                }
            })
        }
    }
}

