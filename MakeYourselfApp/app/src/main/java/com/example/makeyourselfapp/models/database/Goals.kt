package com.example.makeyourselfapp.models.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Goals(
    var id: String = "",
    var name: String? = "",
    var description: String? = "",
    @SerialName("id_sphere")
    var idSphere: Int? = null,
    var status: Boolean = false,
    @SerialName("id_user")
    var idUser: String = ""
)