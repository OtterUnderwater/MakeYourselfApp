package com.example.makeyourselfapp.models.screens

import com.example.makeyourselfapp.models.database.Categories
import com.example.makeyourselfapp.models.database.Tasks

data class StateMenu(
    var listTasks: MutableList<Tasks> = mutableListOf<Tasks>(),
    var listCategories: List<Categories> = listOf(),
    var loading: Boolean = true
)