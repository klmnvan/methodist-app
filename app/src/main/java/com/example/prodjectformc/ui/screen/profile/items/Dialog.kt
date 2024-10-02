package com.example.prodjectformc.ui.screen.profile.items

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.prodjectformc.data.model.general.CurrentUser.themes
import com.example.prodjectformc.data.repository.PrefManager
import com.example.prodjectformc.ui.theme.custom.NewsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationThemePickerDialog(chosenTheme: (String) -> Unit, onDismissRequest: () -> Unit){
    var onThemeChosenValue = PrefManager.theme
    Dialog (onDismissRequest = { onDismissRequest() }) {
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
