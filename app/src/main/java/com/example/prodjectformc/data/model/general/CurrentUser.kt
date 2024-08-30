package com.example.prodjectformc.data.model.general

import com.example.prodjectformc.data.model.event.EventModel
import com.example.prodjectformc.data.model.event.FormOfWork
import com.example.prodjectformc.ui.theme.custom.ThemeMode

object CurrentUser {
    var token = ""
    var accountInfo: AccountInfo? = AccountInfo()
    var selectedTypeOfWork: FormOfWork = FormOfWork()
    var themes = listOf(ThemeMode.Light, ThemeMode.Dark, ThemeMode.Space)
}