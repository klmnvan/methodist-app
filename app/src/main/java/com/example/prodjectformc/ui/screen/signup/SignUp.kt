package com.example.prodjectformc.ui.screen.signup

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navHostController: NavHostController?, viewModel: SignUpViewModel = hiltViewModel()) {
    val state = viewModel.state
    viewModel.context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NewsTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(vertical = 50.dp)) {
            TextTittleForm("Регистрация")
            Spacer(modifier = Modifier.height(4.dp))
            TextDescription("Зарегистрируйтесь, чтобы пользоваться функциями приложения")
            Spacer(modifier = Modifier.height(30.dp))
            TextTittleAuth("Фамилия")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.surname, { viewModel.updateState(viewModel.state.copy(surname = it)) }, "Иванов") {
                viewModel.updateState(viewModel.state.copy(surname = ""))
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleAuth("Имя")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.name, { viewModel.updateState(viewModel.state.copy(name = it)) }, "Иван") {
                viewModel.updateState(viewModel.state.copy(name = ""))
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleAuth("Отчество")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.patronymic, { viewModel.updateState(viewModel.state.copy(patronymic = it)) }, "Иванович") {
                viewModel.updateState(viewModel.state.copy(patronymic = ""))
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleAuth("Логин")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.email, { viewModel.updateState(viewModel.state.copy(email = it)) }, "user@mail.ru") {
                viewModel.updateState(viewModel.state.copy(email = ""))
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleAuth("Пароль")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.password, { viewModel.updateState(viewModel.state.copy(password = it)) }, "******") {
                viewModel.updateState(viewModel.state.copy(password = ""))
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleAuth("Подтвердите пароль")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldAuth(state.passwordConfirm, { viewModel.updateState(viewModel.state.copy(passwordConfirm = it)) }, "******") {
                viewModel.updateState(viewModel.state.copy(passwordConfirm = ""))
            }
            Spacer(modifier = Modifier.height(30.dp))
            MaxWidthButton(
                text = "Далее",
                onClick = {
                    viewModel.signUp(navHostController!!)
                },
                enabled =
                state.email.isNotEmpty() && state.password.isNotEmpty() && state.passwordConfirm.isNotEmpty()
                        && state.name.isNotEmpty() && state.surname.isNotEmpty() && state.patronymic.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = NewsTheme.colors.onPrimary)) {
                        append("Уже есть аккаунт? ")
                    }
                    withStyle(SpanStyle(color = NewsTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                        append("Авторизуйтесь!")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navHostController!!.navigate(RoutesNavigation.LOGIN)
                    },
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                style = NewsTheme.typography.bodyMedium
            )
        }
    }

}
