package com.example.prodjectformc.ui.screen.signin

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
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import com.example.prodjectformc.ui.theme.custom.Black
import com.example.prodjectformc.ui.theme.custom.Blue
import com.example.prodjectformc.ui.theme.custom.Gray1
import com.example.prodjectformc.ui.theme.custom.Gray2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(navHostController: NavHostController?, viewModel: SignInViewModel = hiltViewModel()) {
    val state = viewModel.state
    viewModel.context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Авторизация",
            Modifier
                .padding(horizontal = 20.dp), style = MaterialTheme.typography.bodyLarge)
        Text(text = "Войдите, чтобы пользоваться функциями приложения", Modifier.padding(horizontal = 20.dp), style = MaterialTheme.typography.bodyMedium)
        //Ввод почты
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.state = viewModel.state.copy(email = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp),
            placeholder = { Text(text = "Логин", style = MaterialTheme.typography.labelMedium) },
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                containerColor = Color(Gray2.value)
            ),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = null) {
                            viewModel.state = viewModel.state.copy(email = "")
                        },
                    tint = Color(Gray1.value)
                )
            },
        )
        //Ввод пароля
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.state = viewModel.state.copy(password = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 14.dp),
            placeholder = { Text(text = "Пароль", style = MaterialTheme.typography.labelMedium) },
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                containerColor = Color(Gray2.value)
            ),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = null) {
                            viewModel.state = viewModel.state.copy(password = "")
                        },
                    tint = Color(Gray1.value)
                )
            },
        )
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
                withStyle(SpanStyle(color = Color(Black.value))) {
                    append("Ещё нет аккаута? ")
                }
                withStyle(SpanStyle(color = Color(Blue.value), fontWeight = FontWeight.Bold)) {
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
            style = MaterialTheme.typography.bodyMedium
        )
    }

}
