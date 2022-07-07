package com.akash.gamifyactivity.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.akash.gamifyactivity.LOGO_FILE
import com.akash.gamifyactivity.model.LogoItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LogoRepository @Inject constructor() {

    // load assets and returns list of logo items
    suspend fun loadQuizFromAsset(app: Context): LiveData<List<LogoItem>?> {

        val logoItems = app.assets.open(LOGO_FILE).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val modelType = object : TypeToken<ArrayList<LogoItem>>() {}.type
                val modelList: List<LogoItem> = Gson().fromJson(jsonReader, modelType)
                modelList
            }
        }
        return liveData(Dispatchers.IO) { emit(logoItems) }
    }
}

