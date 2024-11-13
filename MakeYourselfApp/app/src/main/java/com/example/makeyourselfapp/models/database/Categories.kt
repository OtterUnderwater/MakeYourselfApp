package com.example.makeyourselfapp.models.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    var id: Int = 0,
    var category: String = ""
)