package com.cybage.myapplication.views.di

import com.cybage.myapplication.views.networkservice.RetrofitService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class RestrofitServiceModule {
    @Provides
    fun provideRetrofitMopdule(): RetrofitService {
        return RetrofitService.getInstance()
    }
}