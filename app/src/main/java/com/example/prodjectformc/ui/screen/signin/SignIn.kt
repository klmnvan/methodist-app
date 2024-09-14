package com.example.prodjectformc.ui.screen.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.ui.components.MaxWidthButton
import com.example.prodjectformc.ui.components.TextDescription
import com.example.prodjectformc.ui.components.TextFieldAuth
import com.example.prodjectformc.ui.components.TextTittleAuth
import com.example.prodjectformc.ui.components.TextTittleForm
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import com.example.prodjectformc.ui.theme.custom.NewsTheme

@Composable
fun SignIn(navHostController: NavHostController?, viewModel: SignInViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    viewModel.context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NewsTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(25.dp))
            TextTittleForm("Авторизация")
            Spacer(modifier = Modifier.height(4.dp))
            TextDescription("Войдите, чтобы пользоваться функциями приложения")
            Spacer(modifier = Modifier.height(30.dp))
            TextTittleAuth("Адрес эл. почты")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.value.email, { viewModel.stateValue = state.value.copy(email = it) }, "user@mail.ru") {
                viewModel.stateValue = state.value.copy(email = "")
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleAuth("Пароль")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.value.password, { viewModel.stateValue = state.value.copy(password = it) }, "********") {
                viewModel.stateValue = state.value.copy(password = "")
            }
            Spacer(modifier = Modifier.height(30.dp))
            MaxWidthButton(
                text = "Далее",
                onClick = {
                    viewModel.signIn(navHostController!!)
                },
                enabled = state.value.email.isNotEmpty() && state.value.password.isNotEmpty())
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = NewsTheme.colors.onPrimary)) {
                        append("Ещё нет аккаута? ")
                    }
                    withStyle(SpanStyle(color = NewsTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                        append("Зарегистрируйтесь!")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                            navHostController!!.navigate(RoutesNavigation.SIGNUP)
                    },
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                style = NewsTheme.typography.bodyMedium
            )
        }
    }

}
