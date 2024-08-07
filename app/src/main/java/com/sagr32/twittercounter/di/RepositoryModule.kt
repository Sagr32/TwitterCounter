package com.sagr32.twittercounter.di

import com.sagr32.twittercounter.data.network.TwitterService
import com.sagr32.twittercounter.data.repositories.MainRepositoryImpl
import com.sagr32.twittercounter.domain.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(twitterService: TwitterService): MainRepository {
        return MainRepositoryImpl(twitterService)
    }
}