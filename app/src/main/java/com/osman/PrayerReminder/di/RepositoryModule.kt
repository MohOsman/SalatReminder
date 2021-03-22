package com.osman.PrayerReminder.di

import com.osman.PrayerReminder.Service.ApiService
import com.osman.PrayerReminder.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepository = MainRepository(apiService)

}