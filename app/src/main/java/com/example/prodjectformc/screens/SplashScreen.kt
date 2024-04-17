package com.example.prodjectformc.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.objects.PrefManager
import com.example.prodjectformc.objects.RoutesNavigation
import com.example.prodjectformc.ui.theme.ProdjectForMCTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController?) {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        LaunchedEffect(key1 = true) {
            delay(1500L)
            //if (PrefManager.act == 0){
                navHostController!!.navigate(RoutesNavigation.LOGIN) {
                    popUpTo(RoutesNavigation.SPLASH) {
                        inclusive = true
                    }
                }
            //}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Splash")
    }

}

/*Для удобного Preview*/
@Preview(showBackground = true)
@Composable
private fun Preview(){
    ProdjectForMCTheme {
        SplashScreen(null)
    }
}