package com.example.makeyourselfapp.models.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tasks(
    var id: String = "",
    @SerialName("id_goal")
    var idGoal: String = "",
    @SerialName("name_task")
    var nameTask: String = "",
    var description: String = "",
    var status: String = "",
    @SerialName("id_category")
    var idCategory: String = ""
)