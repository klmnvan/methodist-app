package com.example.prodjectformc.ui.screen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.theme.ProdjectForMCTheme

@Composable
fun SignIn(navHostController: NavHostController?, viewModel: SignInViewModel = hiltViewModel()) {
    viewModel.launch(navHostController!!, LocalConfiguration.current)
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.logotype, imageLoader),
            contentDescription = "",
            Modifier.fillMaxWidth().padding(horizontal = 130.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(text = "Splash")
    }

}

/*Для удобного Preview*/
@Preview(showBackground = true)
@Composable
private fun Preview(){
    ProdjectForMCTheme {
        SignIn(null)
    }
}