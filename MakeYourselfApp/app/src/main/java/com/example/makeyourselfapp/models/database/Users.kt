package com.example.makeyourselfapp.models.database

import kotlinx.serialization.Serializable

@Serializable
data class Users(
    var id: String = "",
    var name: String = "",
    var login: String = ""
)
