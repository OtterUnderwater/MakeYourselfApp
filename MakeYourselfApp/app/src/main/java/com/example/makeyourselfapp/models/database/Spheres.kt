package com.example.makeyourselfapp.models.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spheres(
    var id: String = "",
    @SerialName("id_user")
    var idUser: String = "",
    var name: String = ""
)
