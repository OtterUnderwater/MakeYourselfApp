package com.example.makeyourselfapp.models.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.makeyourselfapp.models.database.Goals

data class StateGoals(
    var completedGoals: List<Goals> = listOf(),
    var notCompletedGoals: List<Goals> = listOf(),
    var loading: MutableState<Boolean> = mutableStateOf(true)
)