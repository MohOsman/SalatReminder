package com.osman.PrayerReminder.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.osman.PrayerReminder.Service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
     val BASE_URL = "http://api.aladhan.com/v1/"
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson{
        return GsonBuilder()
            .create();
    }

    @Singleton
    @Provides
    fun porvideRerofit(gson:Gson): Retrofit.Builder{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    }
    @Singleton
    @Provides
    fun provideRestService(retrofit: Retrofit.Builder) : ApiService {
        return retrofit.build().create(ApiService::class.java);
    }
}