package com.example.makeyourselfapp.domain.repository

import android.content.Context
import android.content.SharedPreferences

object PrefManager {
    private lateinit var statusSystem: SharedPreferences

    // Чтобы создать или изменить общие настройки
    fun init(context: Context){
        statusSystem = context.getSharedPreferences("statusSystem", Context.MODE_PRIVATE)
    }

    var status: Int
        get() = statusSystem.getInt("status", 0)
        set(value) = statusSystem.edit().putInt("status", value).apply()
}