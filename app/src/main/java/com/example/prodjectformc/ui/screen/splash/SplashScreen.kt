package com.example.prodjectformc.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.components.SpacerHeight
import com.example.prodjectformc.ui.components.TextDescription
import com.example.prodjectformc.ui.theme.custom.NewsTheme

@Composable
fun SplashScreen(navHostController: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {
    viewModel.context = LocalContext.current
    viewModel.launch(navHostController, LocalConfiguration.current)
    Box(
        Modifier
            .fillMaxSize().background(NewsTheme.colors.background)
            .padding(horizontal = 30.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                imageVector = ImageVector.vectorResource(R.drawable.logo),
                contentDescription = "",
                Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            SpacerHeight(12.dp)
            Text("Beta version", style = NewsTheme.typography.labelMedium.copy(color = NewsTheme.colors.primary))
        }

    }
}
