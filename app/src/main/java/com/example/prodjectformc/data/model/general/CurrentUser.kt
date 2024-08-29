package com.example.prodjectformc.data.model.general

import com.example.prodjectformc.data.model.event.EventModel
import com.example.prodjectformc.data.model.event.FormOfWork

object CurrentUser {
    var token = ""
    var accountInfo: AccountInfo? = null
    var selectedTypeOfWork: FormOfWork = FormOfWork()
}