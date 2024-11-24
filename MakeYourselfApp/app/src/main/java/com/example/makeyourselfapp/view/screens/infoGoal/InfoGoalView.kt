package com.example.makeyourselfapp.view.screens.infoGoal

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.view.components.TextTittle

@Composable
fun InfoGoalView (controller: NavController, goal: Goals, viewModel: InfoGoalViewModel = hiltViewModel()) {


   TextTittle(goal.name!!)


}