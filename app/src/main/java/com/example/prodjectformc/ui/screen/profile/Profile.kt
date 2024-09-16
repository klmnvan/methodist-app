package com.example.prodjectformc.ui.screen.profile

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.general.CurrentUser.themes
import com.example.prodjectformc.data.repository.PrefManager
import com.example.prodjectformc.ui.components.TextTittleForm
import com.example.prodjectformc.ui.components.TextTittle
import com.example.prodjectformc.ui.theme.custom.Blue20
import com.example.prodjectformc.ui.theme.custom.Blue80
import com.example.prodjectformc.ui.theme.custom.Gray3
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.ThemeMode
import com.example.prodjectformc.ui.theme.custom.White
import com.example.prodjectformc.ui.theme.firstCharUp

@Composable
fun Profile(navHostController: NavHostController, currentThemeMode: MutableState<ThemeMode>, viewModel: ProfileViewModel = hiltViewModel()) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NewsTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 20.dp)) {
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
                Text(text = "${state.surname.firstCharUp()} ${state.name.firstCharUp()} ${state.patronymic.firstCharUp()}", style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.onPrimary, fontSize = 20.sp), fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = CurrentUser.accountInfo?.email ?: "", style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.onSecondary, fontSize = 16.sp), fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = CurrentUser.accountInfo?.methodicalCommision?.name ?: "", style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.primary, fontSize = 16.sp), fontWeight = FontWeight.SemiBold,
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
                    TextTittle("Тема приложения")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = currentThemeMode.value.title,
                        modifier = Modifier
                            .background(Color(Blue20.value), RoundedCornerShape(15.dp))
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 16.dp, vertical = 18.dp),
                        style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.primary,
                            fontSize = 16.sp)
                    )
                    var showDialog by remember { mutableStateOf(false) }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(1f),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(Blue80.value)
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

                        ApplicationThemePickerDialog({ currentThemeMode.value = themes.find { theme -> theme.title == it }!!
                            Log.d("currentThemeMode", currentThemeMode.value.toString())}
                        ) {
                            PrefManager.theme = currentThemeMode.value.title
                            showDialog = false
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
            .padding(bottom = 30.dp), verticalArrangement = Arrangement.Bottom) {
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


@Composable
fun ApplicationThemePickerDialog(chosenTheme: (String) -> Unit, onDismissRequest: () -> Unit){
    var onThemeChosenValue = PrefManager.theme
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = NewsTheme.colors.primaryContainer,
            ),
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 5.dp)
            ) {
                Text(
                    text = "Список тем",
                    style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.onPrimary),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))
                ThemeSelectionSection(
                    onThemeChosen = {onThemeChosenValue = it}
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NewsTheme.colors.primary
                    ),
                    onClick = {
                        chosenTheme(onThemeChosenValue)
                        onDismissRequest()
                    }
                ) {
                    Text(
                        text = "Выбрать",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ThemeSelectionSection(
    onThemeChosen: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Log.d("тема текущая", PrefManager.theme)
        Log.d("тема текущая", themes.toString())
        Log.d("тема текущая", themes.map { it.title }.indexOf(PrefManager.theme).toString())
        val firstIndex = themes.map { it.title }.indexOf(PrefManager.theme)
        Box(modifier = Modifier.weight(1f)){
            ThemeInfiniteItemsPicker(
                items = themes.map { it.title },
                firstIndex = Int.MAX_VALUE / 2 + firstIndex - 1,
                onItemSelected = onThemeChosen
            )
        }
    }
}

@Composable
fun ThemeInfiniteItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {

    val listState = rememberLazyListState(firstIndex)
    var currentValue = PrefManager.theme

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Column(modifier = Modifier
        .height(106.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE, itemContent = {
                    val index = it % items.size
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentValue = items[index]
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = items[index],
                        modifier = Modifier
                            .alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f)
                            .weight(1f),
                        style = NewsTheme.typography.headlineMedium.copy(color = NewsTheme.colors.onSecondary),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                })
            }
        )
    }
}
