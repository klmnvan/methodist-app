package com.example.prodjectformc.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
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
import com.example.prodjectformc.ui.screen.bottombar.BottomBar
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.ThemeMode
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("ProduceStateDoesNotAssignValue")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrefManager.init(LocalContext.current)
            CurrentUser.token = PrefManager.token
            PrefManager.checkToken()
            val currentThemeMode = remember { mutableStateOf(CurrentUser.themes.first { it.title == PrefManager.theme }) }
            val isBottomBarVisible = remember { mutableStateOf(false) }
            val controller = rememberNavController()
            NewsTheme(
                themeMode = currentThemeMode.value
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(NewsTheme.colors.background),
                    bottomBar = {
                        if (isBottomBarVisible.value) {
                            BottomBar(
                                navController = controller,
                            )
                        }
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        RootNavigationGraph(controller, isBottomBarVisible, currentThemeMode)
                    }
                }
            }
        }
    }
}







