package com.example.prodjectformc.data.model.general

import com.example.prodjectformc.ui.screen.createevent.ModelTypeOfWork

object CurrentUser {
    var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzNDVoMDk4YmI4cmViZXJid3I0dnZiODk0NSIsImp0aSI6IjkxZjY1MDU1LTRhNDctNDAxYi1iM2ZiLTlhZGQ3NzU1Mzk0NSIsImlhdCI6IjE3MjM3ODkyMDkiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6ImRkMzhmMzJkLTc1ODYtNGM1Yi05ZGExLTM4YTYxY2MxN2VhYyIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiJuZXNrbG1udmFuQGdtYWlsLmNvbSIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL2VtYWlsYWRkcmVzcyI6Im5lc2tsbW52YW5AZ21haWwuY29tIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoi0J_RgNC10L_QvtC00LDQstCw0YLQtdC70YwiLCJleHAiOjE3MjM4NzU2MDksImlzcyI6IkV4YW1wbGVJc3N1ZXIiLCJhdWQiOiJWYWxpZEF1ZGllbmNlIn0.PHyENhl-1FYCOhPoAYUD5gJ-7a4ClmOnzS_cZ3HlApc"
    var accountInfo: AccountInfo? = null
    var listEvents: MutableList<EventModel>? = ArrayList()
    var selectedTypeOfWork: FormOfWork = FormOfWork()
}