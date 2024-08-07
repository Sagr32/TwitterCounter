package com.sagr32.counterview.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

//    @Provides
//    fun provideViewModelFactory(): ViewModelProvider.Factory {
//        return ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
//    }
}