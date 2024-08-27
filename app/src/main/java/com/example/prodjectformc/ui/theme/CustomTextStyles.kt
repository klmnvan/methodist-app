package com.example.prodjectformc.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

val buttonTextStyle = androidx.compose.ui.text.TextStyle(
    fontFamily = Raleway,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    color = Color(White.value)
)

val blueTextPoppins = androidx.compose.ui.text.TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = Color(White.value)
)

fun String.firstCharUp(): String {
    if (isEmpty()) return this
    return this.first().uppercase() + this.substring(1)
}

fun String.convertDate(): String {
    return if (this.isNotEmpty() && this != ""){
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val localDate = LocalDate.parse(this, inputFormatter)
        localDate.format(outputFormatter)
    }
    else this
}

fun String.convertDateDefaultFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))

    val date = inputFormat.parse(this)

    return outputFormat.format(date)  // Вывод: 9 сентября 2022
}