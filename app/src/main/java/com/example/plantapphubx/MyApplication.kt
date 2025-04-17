package com.example.plantapphubx

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Uygulama başlatıldığında yapılacak işlemler
    }
}