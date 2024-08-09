package com.example.prodjectformc.data.model.general

object CurrentUser {
    var token = ""
    var accountInfo: AccountInfo? = null
    var listEvents: MutableList<EventModel>? = ArrayList()
}