package com.example.prodjectformc.ui.screen.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.composablefunc.MaxWidthButton
import com.example.prodjectformc.ui.composablefunc.TextDescription
import com.example.prodjectformc.ui.composablefunc.TextFieldAuth
import com.example.prodjectformc.ui.composablefunc.TextTittle
import com.example.prodjectformc.ui.composablefunc.TextTittleAuth
import com.example.prodjectformc.ui.composablefunc.TextTittleForm
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import com.example.prodjectformc.ui.theme.custom.Black
import com.example.prodjectformc.ui.theme.custom.Blue
import com.example.prodjectformc.ui.theme.custom.Gray1
import com.example.prodjectformc.ui.theme.custom.Gray2
import com.example.prodjectformc.ui.theme.custom.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(navHostController: NavHostController?, viewModel: SignInViewModel = hiltViewModel()) {
    val state = viewModel.state
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
            //Ввод почты
            Spacer(modifier = Modifier.height(30.dp))
            TextTittleAuth("Адрес эл. почты")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.email, { viewModel.updateState(viewModel.state.copy(email = it)) }, "user@mail.ru") {
                viewModel.updateState(viewModel.state.copy(email = ""))
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleAuth("Пароль")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.password, { viewModel.updateState(viewModel.state.copy(password = it)) }, "********") {
                viewModel.updateState(viewModel.state.copy(password = ""))
            }
            Spacer(modifier = Modifier.height(30.dp))
            MaxWidthButton(
                text = "Далее",
                onClick = {
                    viewModel.signIn(navHostController!!)
                },
                enabled = state.email.isNotEmpty() && state.password.isNotEmpty())
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
