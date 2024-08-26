package com.example.prodjectformc.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.ui.composablefunc.MaxWidthButton
import com.example.prodjectformc.ui.composablefunc.TextFieldForm
import com.example.prodjectformc.ui.composablefunc.TextTittleForm
import com.example.prodjectformc.ui.composablefunc.TextTittleFormTextField
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Blue20
import com.example.prodjectformc.ui.theme.Blue80
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.Gray3
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.White

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
            Spacer(modifier = Modifier.height(24.dp))
            TextTittleForm("Профиль")
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier
                    .background(Color(Gray3.value), RoundedCornerShape(15.dp))
                    .height(110.dp)
                    .width(110.dp))
                Spacer(modifier = Modifier.width(28.dp))
                Column {
                    TextTittleFormTextField("Роль")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = CurrentUser.accountInfo?.role!!.get(0), modifier = Modifier
                        .background(Color(Blue20.value), RoundedCornerShape(14.dp))
                        .border(BorderStroke(1.dp, Color(Blue.value)), RoundedCornerShape(15.dp))
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                        color = Color(Blue.value),
                        fontFamily = Raleway,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Методическая комиссия")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = CurrentUser.accountInfo?.methodicalCommision!!.name, modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color(Blue.value)), RoundedCornerShape(15.dp))
                .background(Color(Blue20.value), RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 18.dp),
                color = Color(Blue.value),
                fontFamily = Raleway,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleFormTextField("Фамилия")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldForm(state.surname,
                { viewModel.updateState(viewModel.state.copy(surname = it)) },
                "Иванов",
                { viewModel.updateState(viewModel.state.copy(surname = "")) }, true, { })
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleFormTextField("Имя")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldForm(state.name,
                { viewModel.updateState(viewModel.state.copy(name = it)) },
                "Иван",
                { viewModel.updateState(viewModel.state.copy(name = "")) }, true, { })
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleFormTextField("Отчество")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldForm(state.patronymic,
                { viewModel.updateState(viewModel.state.copy(patronymic = it)) },
                "Иванович", { viewModel.updateState(viewModel.state.copy(patronymic = ""))},
                true, { })
            Spacer(modifier = Modifier.height(30.dp))
        }
        MaxWidthButton(
            text = "Сохранить",
            onClick = {

            },
            enabled = (CurrentUser.accountInfo!!.name != state.name ||
                    CurrentUser.accountInfo!!.surname != state.surname ||
                    CurrentUser.accountInfo!!.patronymic != state.patronymic) )
        Spacer(modifier = Modifier.height(30.dp))
    }


}

