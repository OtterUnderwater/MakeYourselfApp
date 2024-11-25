package com.example.makeyourselfapp.models.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres

data class StateStatistics(
    var listGoals: List<Goals> = listOf(),
    var listSpheres: List<Spheres> = listOf(),
    var loading: MutableState<Boolean> = mutableStateOf(true)
)