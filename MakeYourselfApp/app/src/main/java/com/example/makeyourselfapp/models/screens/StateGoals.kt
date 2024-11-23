package com.example.makeyourselfapp.models.screens

import com.example.makeyourselfapp.models.database.Goals

data class StateGoals(
    var completedGoals: List<Goals>? = null,
    var notCompletedGoals: List<Goals>? = null,
    var loading: Boolean = true
)