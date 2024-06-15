package com.example.prodjectformc.ui.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import com.example.prodjectformc.ui.theme.Black
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.Gray3
import com.example.prodjectformc.ui.theme.ProdjectForMCTheme
import com.example.prodjectformc.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navHostController: NavHostController?, viewModel: SignUpViewModel = hiltViewModel()) {
    val state = viewModel.state
    viewModel.context = LocalContext.current
    //viewModel.context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Регистрация", Modifier.padding(top = 25.dp), style = MaterialTheme.typography.bodyLarge)
        Text(text = "Зарегистрируйтесь, чтобы пользоваться функциями приложения",
            Modifier.padding(horizontal = 40.dp), style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = state.surname,
            onValueChange = { viewModel.state = viewModel.state.copy(surname = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            placeholder = { Text(text = "Фамилия", style = MaterialTheme.typography.labelMedium) },
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
                        .clickable {
                            viewModel.state = viewModel.state.copy(email = "")
                        },
                    tint = Color(Gray1.value)
                )
            },
        )

        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            value = state.name,
            onValueChange = { viewModel.state = viewModel.state.copy(name = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            placeholder = { Text(text = "Имя", style = MaterialTheme.typography.labelMedium) },
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
                        .clickable {
                            viewModel.state = viewModel.state.copy(password = "")
                        },
                    tint = Color(Gray1.value)
                )
            },
        )

        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            value = state.patronymic,
            onValueChange = { viewModel.state = viewModel.state.copy(patronymic = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            placeholder = { Text(text = "Отчество", style = MaterialTheme.typography.labelMedium) },
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
                        .clickable {
                            viewModel.state = viewModel.state.copy(password = "")
                        },
                    tint = Color(Gray1.value)
                )
            },
        )

        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.state = viewModel.state.copy(email = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
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
                        .clickable {
                            viewModel.state = viewModel.state.copy(password = "")
                        },
                    tint = Color(Gray1.value)
                )
            },
        )

        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.state = viewModel.state.copy(password = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
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
                        .clickable {
                            viewModel.state = viewModel.state.copy(password = "")
                        },
                    tint = Color(Gray1.value)
                )
            },
        )

        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            value = state.passwordConfirm,
            onValueChange = { viewModel.state = viewModel.state.copy(passwordConfirm = it) },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
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
                        .clickable {
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
                viewModel.signUp(navHostController!!)
            },
            enabled =
            state.email.isNotEmpty() && state.password.isNotEmpty() && state.passwordConfirm.isNotEmpty()
                    && state.name.isNotEmpty() && state.surname.isNotEmpty() && state.patronymic.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color(Black.value))) {
                    append("Уже есть аккаунт? ")
                }
                withStyle(SpanStyle(color = Color(Blue.value), fontWeight = FontWeight.Bold)) {
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
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
fun MaxWidthButton(text: String, onClick: () -> Unit, enabled: Boolean = true) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(White.value),
            containerColor = Color(Blue.value),
            disabledContainerColor = Color(Gray3.value),
            disabledContentColor = Color(White.value)
        ),
        shape = RoundedCornerShape(10.dp),
        enabled = enabled
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

/*Для удобного Preview*/
@Preview(showBackground = true)
@Composable
private fun Preview(){
    ProdjectForMCTheme {
        SignUp(null)
    }
}