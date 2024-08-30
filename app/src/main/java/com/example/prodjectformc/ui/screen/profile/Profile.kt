package com.example.prodjectformc.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.ui.composablefunc.CustomDatePickerDialog
import com.example.prodjectformc.ui.composablefunc.MaxWidthButton
import com.example.prodjectformc.ui.composablefunc.TextFieldForm
import com.example.prodjectformc.ui.composablefunc.TextTittleForm
import com.example.prodjectformc.ui.composablefunc.TextTittleFormTextField
import com.example.prodjectformc.ui.composablefunc.monthsNames
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.custom.Blue
import com.example.prodjectformc.ui.theme.custom.Blue20
import com.example.prodjectformc.ui.theme.custom.Blue80
import com.example.prodjectformc.ui.theme.custom.Gray3
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.White
import java.util.ArrayList

@Composable
fun Profile(navHostController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NewsTheme.colors.background)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp).verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextTittleForm("Профиль")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Box(modifier = Modifier
                    .background(Color(Gray3.value), RoundedCornerShape(100.dp))
                    .height(110.dp)
                    .width(110.dp))
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Иванов Иван Иванович", style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.onPrimary, fontSize = 20.sp), fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "ivanov_ivan@mail.com | Преподаватель", style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.onSecondary, fontSize = 16.sp), fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Информационные системы", style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.primary, fontSize = 16.sp), fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(Blue80.value)
                    ),
                    onClick = {

                    }) {
                    Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(modifier = Modifier
                            .padding()
                            .size(25.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.icon_profile), contentDescription = "",
                            tint = Color(White.value)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Изменить профиль", style = NewsTheme.typography.buttonTextStyle.copy(fontSize = 16.sp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    TextTittleFormTextField("Тема приложения")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = "Тёмная",
                        modifier = Modifier
                            .background(Color(Blue20.value), RoundedCornerShape(15.dp))
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 16.dp, vertical = 18.dp),
                        style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.primary, fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp)
                    )
                    var showDialog by remember { mutableStateOf(false) }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(1f),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(Blue.value)
                        ),
                        onClick = {
                            showDialog = true
                        }) {
                        Icon(modifier = Modifier
                            .padding()
                            .size(25.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.icon_brush), contentDescription = "",
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Сменить", style = NewsTheme.typography.buttonTextStyle.copy(fontSize = 16.sp))
                    }

                    if (showDialog) {

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
        Column(modifier = Modifier.fillMaxHeight().padding(horizontal = 20.dp).padding(bottom = 30.dp), verticalArrangement = Arrangement.Bottom) {
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                          viewModel.logOut(navHostController)
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(White.value),
                    containerColor = NewsTheme.colors.error,
                    disabledContainerColor = NewsTheme.colors.inversePrimary,
                    disabledContentColor = Color(White.value)),
            ) {
                Text(
                    text = "Выйти",
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }


}

