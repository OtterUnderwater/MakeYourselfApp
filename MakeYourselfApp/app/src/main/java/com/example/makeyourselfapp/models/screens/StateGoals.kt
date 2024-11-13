package com.example.makeyourselfapp.models.screens

import com.example.makeyourselfapp.models.database.Goals

data class StateGoals(
    var completedGoals: List<Goals> = listOf(),
    var notCompletedGoals: List<Goals> = listOf()
)