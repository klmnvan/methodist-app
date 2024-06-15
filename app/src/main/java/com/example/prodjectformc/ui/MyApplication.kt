package com.example.prodjectformc.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Для подключения Hilt к проекту, чтобы данный класс сработал, его нужно в манифесте подключить
@HiltAndroidApp
class MyApplication: Application() {
}