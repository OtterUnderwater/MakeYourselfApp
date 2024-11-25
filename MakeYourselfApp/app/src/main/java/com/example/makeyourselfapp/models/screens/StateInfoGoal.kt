package com.example.makeyourselfapp.models.screens

import com.example.makeyourselfapp.models.database.Categories
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres
import com.example.makeyourselfapp.models.database.Tasks


data class StateInfoGoal(
    var goal: Goals = Goals(),
    var listSpheres: List<Spheres> = listOf(),
    var listTasks: MutableList<Tasks> = mutableListOf<Tasks>(),
    var listCategories: List<Categories> = listOf(),
    var loading: Boolean = true
)