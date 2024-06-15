package com.example.prodjectformc.data.objects

import android.content.Context
import android.content.SharedPreferences

object PrefManager {
    private lateinit var spAct: SharedPreferences

    fun init(context: Context){
        spAct = context.getSharedPreferences("ActSystem", Context.MODE_PRIVATE)
    }

    var act: Int
        get() = spAct.getInt("act", 0)
        set(value) = spAct.edit().putInt("act", value).apply()
}