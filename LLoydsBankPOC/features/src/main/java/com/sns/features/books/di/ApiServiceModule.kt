package com.sns.features.books.di

import com.sns.features.books.data.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideSearchAppService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
}