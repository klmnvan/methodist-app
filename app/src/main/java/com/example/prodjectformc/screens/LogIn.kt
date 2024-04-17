package com.example.prodjectformc.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.composablefunc.AuthFieldComponent
import com.example.prodjectformc.composablefunc.ButtonBlueGrayMP
import com.example.prodjectformc.composablefunc.TextBold
import com.example.prodjectformc.composablefunc.TextNormal
import com.example.prodjectformc.objects.Modifiers.MDBtnDefaultMP
import com.example.prodjectformc.ui.theme.Black
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.ProdjectForMCTheme
import com.example.prodjectformc.ui.theme.inter
import com.example.prodjectformc.viewmodels.MainViewModel


@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun LogIn(navHostController: NavHostController?) {

    var inptEmail by remember {
        mutableStateOf("")
    }

    val viewmodel = viewModel<MainViewModel>()
    val state = viewmodel.state.value

    val context = LocalContext.current

    var inptPass1 by remember {
        mutableStateOf("")
    }

    var buttonIsClicable by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(inptEmail, inptPass1) {
        if (inptEmail.isNotEmpty() && inptPass1.isNotEmpty()) {
            buttonIsClicable = true
        } else {
            buttonIsClicable = false
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 30.dp)
    ) {
        Column {
            TextBold(
                text = "Авторизация.",
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 20.dp), 24, Color(Black.value))
            TextNormal("Войдите, чтобы пользоваться функциями приложения",
                Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 7.dp), 14, Color(Black.value)
            )
            AuthFieldComponent(inptEmail, { inptEmail = it }, "Введите логин")
            AuthFieldComponent(inptPass1, { inptEmail = it }, "Введите пароль")

            ButtonBlueGrayMP(
                {
                    viewmodel.sendCodeToEmail("nesklmnvan@gmail.com")
            }, MDBtnDefaultMP.padding(top = 100.dp),
                buttonIsClicable,"Далее")

            if (state.loading) {
                CircularProgressIndicator()
            } else {
                state.error?.let { Text(text = it) }
            }
        }
    }
}


/*Для удобного Preview*/
@Preview(showBackground = true)
@Composable
private fun Preview() {
    ProdjectForMCTheme {
        LogIn(null)
    }
}