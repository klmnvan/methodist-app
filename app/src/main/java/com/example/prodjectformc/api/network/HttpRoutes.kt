package com.example.prodjectformc.api.network

object HttpRoutes {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    private const val URL_POODLE = "https://iis.ngknn.ru/NGKNN/МамшеваЮС/MedicMadlab"
    const val POSTS = "$BASE_URL/posts"
    const val CATALOG = "$URL_POODLE/api/Catalog"
    const val SENDCODE = "$URL_POODLE/api/SendCode"
    const val SIGNIN = "$URL_POODLE/api/SignIn"
}