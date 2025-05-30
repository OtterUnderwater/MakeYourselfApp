package com.example.makeyourselfapp.models.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.makeyourselfapp.models.database.Categories
import com.example.makeyourselfapp.models.database.Tasks

data class StateMenu(
    var listTasks: MutableList<Tasks> = mutableListOf<Tasks>(),
    var listCategories: List<Categories> = listOf(),
    var loading: MutableState<Boolean> = mutableStateOf(true)
)