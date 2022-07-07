package com.akash.gamifyactivity.di.modules

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAssetManager(context: Context) : AssetManager{
        return context.assets
    }
}