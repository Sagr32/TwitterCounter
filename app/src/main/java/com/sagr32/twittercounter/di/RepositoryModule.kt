package com.sagr32.twittercounter.di

import com.sagr32.counterview.data.repositories.CounterRepositoryImpl
import com.sagr32.counterview.data.source.TweetValidatorImpl
import com.sagr32.counterview.domain.repositories.CounterRepository
import com.sagr32.counterview.domain.source.TweetValidator
import com.sagr32.counterview.domain.usecases.CalculateTweetLengthUseCase
import com.sagr32.counterview.domain.usecases.CheckTweetValidityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideTweetDataValidator(): TweetValidator {
//        return TweetValidatorImpl()
//    }
//
//    @Provides
//    @Singleton
//    fun provideCounterRepository(
//        tweetDataSource: TweetValidator
//    ): CounterRepository {
//        return CounterRepositoryImpl(tweetDataSource)
//    }
//
//    @Provides
//    @Singleton
//    fun provideCalculateTweetLengthUseCase(
//        counterRepository: CounterRepository
//    ): CalculateTweetLengthUseCase {
//        return CalculateTweetLengthUseCase(counterRepository)
//    }
//
//    @Provides
//    @Singleton
//    fun provideCheckTweetValidityUseCase(
//        counterRepository: CounterRepository
//    ): CheckTweetValidityUseCase {
//        return CheckTweetValidityUseCase(counterRepository)
//    }
//}
