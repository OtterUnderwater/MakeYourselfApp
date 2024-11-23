package com.example.makeyourselfapp.models.screens

import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres

data class StateStatistics(
    var listGoals: List<Goals> = listOf(),
    var listSpheres: List<Spheres> = listOf(),
    var loading: Boolean = true
)