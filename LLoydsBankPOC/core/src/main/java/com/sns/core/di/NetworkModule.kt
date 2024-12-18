package com.sns.core.di

import com.sns.core.constants.AppConstants
import com.sns.core.constants.AppConstants.BUIL_CONFIG_DEBUG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        val interceptor = HttpLoggingInterceptor()
        if (BUIL_CONFIG_DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
       } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
       }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

