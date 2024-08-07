package com.sagr32.twittercounter.di

import com.sagr32.twittercounter.data.network.OAuthInterceptor
import com.sagr32.twittercounter.data.network.TwitterService
import com.sagr32.twittercounter.utils.Constants.ACCESS_TOKEN
import com.sagr32.twittercounter.utils.Constants.ACCESS_TOKEN_SECRET
import com.sagr32.twittercounter.utils.Constants.API_KEY
import com.sagr32.twittercounter.utils.Constants.API_SECRET_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("consumerKey")
    fun provideConsumerKey(): String {
        return API_KEY
    }

    @Provides
    @Named("consumerSecret")
    fun provideConsumerSecret(): String {
        return API_SECRET_KEY
    }

    @Provides
    @Named("token")
    fun provideToken(): String {
        return ACCESS_TOKEN
    }

    @Provides
    @Named("tokenSecret")
    fun provideTokenSecret(): String {
        return ACCESS_TOKEN_SECRET
    }
    @Provides
    @Singleton
    fun provideOAuthInterceptor(
        @Named("consumerKey") consumerKey: String,
        @Named("consumerSecret") consumerSecret: String,
        @Named("token") token: String,
        @Named("tokenSecret") tokenSecret: String
    ): OAuthInterceptor {
        return OAuthInterceptor(
            consumerKey = consumerKey,
            consumerSecret = consumerSecret,
            token = token,
            tokenSecret = tokenSecret
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        oauthInterceptor: OAuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(oauthInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }



    @Provides
    @Singleton
    fun provideTwitterService(retrofit: Retrofit): TwitterService {
        return retrofit.create(TwitterService::class.java)
    }
}

