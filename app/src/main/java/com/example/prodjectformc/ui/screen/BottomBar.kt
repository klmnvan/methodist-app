package com.example.prodjectformc.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.prodjectformc.ui.navigation.Destinations
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.Gray3
import com.example.prodjectformc.ui.theme.White

@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    val screens = listOf(Destinations.HomeScreen, Destinations.Profile)

    NavigationBar(
        modifier = modifier,
        containerColor = Color(White.value),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->

            Column(modifier = Modifier.weight(1f).clickable {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },horizontalAlignment = Alignment.CenterHorizontally) {
                var selectedColor = Color(Gray3.value)
                if(currentRoute == screen.route) {
                    selectedColor = Color(Blue.value)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Icon(imageVector = ImageVector.vectorResource(id = screen.resourceId!!), contentDescription = "", tint = selectedColor)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = screen.title!!, color = selectedColor, fontSize = 12.sp)
            }
        }
    }

}