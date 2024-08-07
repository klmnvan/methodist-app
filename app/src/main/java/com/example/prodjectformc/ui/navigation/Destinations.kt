package com.example.prodjectformc.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.prodjectformc.R

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val resourceId: Int? = null
) {
    object HomeScreen : Destinations(
        route = "home_screen",
        title = "Мероприятия",
        resourceId = R.drawable.icon_home
    )

    object Profile : Destinations(
        route = "profile_screen",
        title = "Профиль",
        resourceId = R.drawable.icon_profile
    )


}