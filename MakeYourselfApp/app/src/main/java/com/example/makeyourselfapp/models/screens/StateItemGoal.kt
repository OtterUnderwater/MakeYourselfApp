package com.example.makeyourselfapp.models.screens

import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres
import com.example.makeyourselfapp.models.database.Tasks

data class StateItemGoal(
    var goals: Goals = Goals(),
    var listSphere: List<Spheres> = listOf(),
    var listTasks: List<Tasks> = listOf(),
    var loading: Boolean = true
)