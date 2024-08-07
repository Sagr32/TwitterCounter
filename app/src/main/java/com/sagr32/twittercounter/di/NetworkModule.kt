package com.sagr32.twittercounter.di

import com.sagr32.twittercounter.data.network.OAuthInterceptor
import com.sagr32.twittercounter.data.network.TwitterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val API_KEY = "IMLpFXxhVDWhAxMrmHec8IUeU"
    const val API_SECRET_KEY = "nbnsNcxJ5zb303K6J8d1gPTLJQaipGCfe0ppRDWKF0eV10ICz0"
    const val ACCESS_TOKEN = "1813218983870394368-52VXNZdIz9R882AOBI1MwTefCXYkGs"
    const val ACCESS_TOKEN_SECRET = "YCXgb7qcuk7jXJvi2m40I3MN0myWE8KxWgS6nPYtqRkCZ"

    @Provides
    @Named("consumerKey")
    fun provideConsumerKey(): String {
        return API_KEY // Replace with your actual consumer key
    }

    @Provides
    @Named("consumerSecret")
    fun provideConsumerSecret(): String {
        return API_SECRET_KEY // Replace with your actual consumer secret
    }

    @Provides
    @Named("token")
    fun provideToken(): String {
        return ACCESS_TOKEN // Replace with your actual token
    }

    @Provides
    @Named("tokenSecret")
    fun provideTokenSecret(): String {
        return ACCESS_TOKEN_SECRET // Replace with your actual token secret
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

