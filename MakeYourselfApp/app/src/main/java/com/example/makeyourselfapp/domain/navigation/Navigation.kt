package com.example.makeyourselfapp.domain.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.view.screens.auth.AuthView
import com.example.makeyourselfapp.view.screens.goals.GoalsView
import com.example.makeyourselfapp.view.screens.infoGoal.InfoGoalView
import com.example.makeyourselfapp.view.screens.itemGoal.ItemGoalView
import com.example.makeyourselfapp.view.screens.menu.MenuView
import com.example.makeyourselfapp.view.screens.reg.RegView
import com.example.makeyourselfapp.view.screens.splash.Splash
import com.example.makeyourselfapp.view.screens.statistics.StatisticsView
import kotlinx.serialization.json.Json

@Composable
fun Navigation(controller: NavHostController, isVisibleBar: MutableState<Boolean>){
    NavHost(
        navController = controller,
        startDestination = RoutesNavigation.SPLASH
    ) {
        composable(RoutesNavigation.SPLASH) {
            Splash(controller, isVisibleBar)
        }
        composable(RoutesNavigation.GOALS) {
            GoalsView(controller)
        }
        composable(RoutesNavigation.MENU) {
            MenuView(controller)
        }
        composable(RoutesNavigation.STATISTICS) {
            StatisticsView(controller)
        }
        composable(RoutesNavigation.AUTH) {
            AuthView(controller)
        }
        composable(RoutesNavigation.REG) {
            RegView(controller)
        }
        composable(RoutesNavigation.ITEM_GOAL) {
            ItemGoalView(controller)
        }
        composable(RoutesNavigation.INFO_GOAL + "/{goal}") { backStackEntry ->
            val goalJson = backStackEntry.arguments?.getString("goal") ?: ""
            val goal = try {
                Json.decodeFromString(Goals.serializer(), goalJson)
            } catch (e: Exception) {
                Log.e("InfoGoalView", "Error decoding Goals: ${e.message}")
                Goals()
            }
            InfoGoalView(controller, goal)
        }
    }
}