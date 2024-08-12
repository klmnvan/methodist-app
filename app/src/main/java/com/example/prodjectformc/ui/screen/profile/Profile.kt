package com.example.prodjectformc.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.composablefunc.MaxWidthButton
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Blue20
import com.example.prodjectformc.ui.theme.Blue80
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.Gray3
import com.example.prodjectformc.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navHostController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF7F7F9))
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Профиль",
                Modifier
                    .align(Alignment.CenterHorizontally), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier
                    .background(Color(Gray3.value), RoundedCornerShape(15.dp))
                    .height(110.dp)
                    .width(110.dp))
                Spacer(modifier = Modifier.width(28.dp))
                Column {
                    Text(text = "Роль", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = state.role, modifier = Modifier
                        .background(Color(Blue.value), RoundedCornerShape(15.dp))
                        .padding(1.dp)
                        .background(Color(White.value), RoundedCornerShape(14.dp))
                        .padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium)
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Фамилия", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.surname,
                onValueChange = { viewModel.state = viewModel.state.copy(surname = it) },
                textStyle = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Иванов", style = MaterialTheme.typography.labelMedium) },
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(Blue80.value),
                    focusedBorderColor = Color(Blue.value),
                    containerColor = Color(White.value)
                ),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(interactionSource = remember { MutableInteractionSource() },
                                indication = null) {
                                viewModel.state = viewModel.state.copy(surname = "")
                            },
                        tint = Color(Gray1.value)
                    )
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Имя", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.name,
                onValueChange = { viewModel.state = viewModel.state.copy(name = it) },
                textStyle = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Иван", style = MaterialTheme.typography.labelMedium) },
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(Blue80.value),
                    focusedBorderColor = Color(Blue.value),
                    containerColor = Color(White.value)
                ),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(interactionSource = remember { MutableInteractionSource() },
                                indication = null) {
                                viewModel.state = viewModel.state.copy(name = "")
                            },
                        tint = Color(Gray1.value)
                    )
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Отчество", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.patronymic,
                onValueChange = { viewModel.state = viewModel.state.copy(patronymic = it) },
                textStyle = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Иванович", style = MaterialTheme.typography.labelMedium) },
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(Blue80.value),
                    focusedBorderColor = Color(Blue.value),
                    containerColor = Color(White.value)
                ),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(interactionSource = remember { MutableInteractionSource() },
                                indication = null) {
                                viewModel.state = viewModel.state.copy(patronymic = "")
                            },
                        tint = Color(Gray1.value)
                    )
                },
            )
            Spacer(modifier = Modifier.height(40.dp))


        }
        MaxWidthButton(
            text = "Далее",
            onClick = {

            },
            enabled = true)
    }


}

