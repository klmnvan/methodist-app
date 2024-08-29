package com.example.prodjectformc.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.repository.PrefManager
import com.example.prodjectformc.ui.navigation.RootNavigationGraph
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.ThemeMode
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("ProduceStateDoesNotAssignValue")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var themes = listOf(ThemeMode.Light, ThemeMode.Dark, ThemeMode.Space)
        setContent {
            val currentThemeMode by remember { mutableStateOf<ThemeMode>(ThemeMode.Dark) }
            NewsTheme(
                themeMode = currentThemeMode
            ) {
                PrefManager.init(LocalContext.current)
                CurrentUser.token = PrefManager.token
                PrefManager.checkToken()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationGraph(rememberNavController())
                }
            }
        }
    }
}





