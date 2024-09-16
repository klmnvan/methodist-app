package com.example.prodjectformc.ui.screen.bottombar

import android.media.Image
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.prodjectformc.ui.navigation.DestinationsBottomBar
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.custom.Black
import com.example.prodjectformc.ui.theme.custom.NewsTheme

@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    val screens = listOf(DestinationsBottomBar.CreateEventScreen, DestinationsBottomBar.HomeScreen, DestinationsBottomBar.ProfileScreen)
    Box(modifier = Modifier) {
        NavigationBar(
            modifier = modifier,
            containerColor = NewsTheme.colors.primaryContainer,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            screens.forEach { screen ->
                if(screen.route == "home_screen"){

                } else {
                    Column(modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if(currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    currentRoute?.let {
                                        popUpTo(it) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        },horizontalAlignment = Alignment.CenterHorizontally) {
                        var selectedColor = NewsTheme.colors.onBackground
                        if(currentRoute == screen.route) {
                            selectedColor = NewsTheme.colors.primary
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Icon(imageVector = ImageVector.vectorResource(id = screen.resourceId!!),
                            modifier = Modifier.size(30.dp),
                            contentDescription = "", tint = selectedColor)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = screen.title!!, color = selectedColor, fontSize = 3.em,
                            fontFamily = Raleway, fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
        Icon(imageVector = ImageVector.vectorResource(id = screens[1].resourceId!!), tint = Color.Unspecified,
            modifier = Modifier.align(Alignment.TopCenter)
                .size(70.dp)
                .offset(y = (-16).dp)
                .background(Color.Transparent).shadow(
                    elevation = 5.dp, shape = RoundedCornerShape(100), spotColor = Color(
                        Black.value
                    ), ambientColor = Color(Black.value)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null) {
                    if(navController.currentDestination!!.route != "home_screen"){
                        navController.navigate(screens[1].route) {
                            popUpTo(DestinationsBottomBar.CreateEventScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                },contentDescription = "")

    }

}