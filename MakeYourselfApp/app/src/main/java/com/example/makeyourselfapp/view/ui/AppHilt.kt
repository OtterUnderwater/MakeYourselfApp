package com.example.makeyourselfapp.view.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Для подключения Hilt к проекту - подключаем класс в манифесте
@HiltAndroidApp
class AppHilt: Application() {
}
