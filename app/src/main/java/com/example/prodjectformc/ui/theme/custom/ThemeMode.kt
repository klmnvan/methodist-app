package com.example.prodjectformc.ui.theme.custom

sealed class ThemeMode(val title: String) {
    data object Light: ThemeMode(title = "Light")
    data object Dark: ThemeMode(title = "Dark")
    data object DarkBlue: ThemeMode(title = "DarkBlue")
}
