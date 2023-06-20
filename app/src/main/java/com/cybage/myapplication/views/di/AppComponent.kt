package com.cybage.myapplication.views.di

import android.content.Context
import com.cybage.myapplication.views.views.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(modules = [RestrofitServiceModule::class])
interface AppComponent {
    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(activity: MainActivity)
}