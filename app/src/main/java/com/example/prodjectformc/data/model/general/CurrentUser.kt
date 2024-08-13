package com.example.prodjectformc.data.model.general

import com.example.prodjectformc.ui.screen.createevent.ModelTypeOfWork

object CurrentUser {
    var token = ""
    var accountInfo: AccountInfo? = null
    var listEvents: MutableList<EventModel>? = ArrayList()
    var selectedTypeOfWork: FormOfWork = FormOfWork()
}