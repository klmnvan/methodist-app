package com.example.prodjectformc.ui.navigation

import com.example.prodjectformc.R

sealed class DestinationsBottomBar(
    val route: String,
    val title: String? = null,
    val resourceId: Int? = null
) {

    object HomeScreen : DestinationsBottomBar(
        route = "home_screen",
        title = "Формы",
        resourceId = R.drawable.button_create_event
    )

    object CreateEventScreen : DestinationsBottomBar(
        route = "event_screen",
        title = "Формы",
        resourceId = R.drawable.icon_home
    )

    object ProfileScreen : DestinationsBottomBar(
        route = "profile_screen",
        title = "Профиль",
        resourceId = R.drawable.icon_profile
    )

}