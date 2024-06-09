package com.example.prodjectformc.ui.screen.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.objects.RoutesNavigation
import com.example.prodjectformc.ui.theme.ProdjectForMCTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController?, viewModel: SplashViewModel = hiltViewModel()) {
    viewModel.launch(navHostController!!, LocalConfiguration.current)
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 130.dp), contentAlignment = Alignment.Center) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.logotype),
            contentDescription = "",
            Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

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