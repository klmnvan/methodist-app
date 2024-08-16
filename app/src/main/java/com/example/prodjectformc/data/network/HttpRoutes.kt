package com.example.prodjectformc.data.network

object HttpRoutes {
    private const val BASE_URL = "https://iis.ngknn.ru/NGKNN/ТаранАА/Api"
    const val LOGIN = "$BASE_URL/Account/Login"
    const val REGISTER = "$BASE_URL/Account/Register"
    const val INFORMATION = "$BASE_URL/Account/Infortmation"
    const val GETEVENT = "$BASE_URL/Events/GetEvent"
    const val GETPARTICIPATIONFORMS = "$BASE_URL/ValuesForms/GetParticipationForms"
    const val GETSTATUSEVENTS = "$BASE_URL/ValuesForms/GetStatusEvents"
    const val GETEVENTFORMS = "$BASE_URL/ValuesForms/GetEventForms"
    const val GETRESULTEVENTS = "$BASE_URL/ValuesForms/GetResultEvents"
    const val FORMOFWORKS = "$BASE_URL/FormOfWorks"
}