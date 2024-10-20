package ru.amalkoott.findjob.di

import android.app.Application
import dagger.Component
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(myApplication: FindjobApplication)
}

@HiltAndroidApp
class FindjobApplication : Application(){
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}