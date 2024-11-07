package com.example.makeyourselfapp.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.makeyourselfapp.view.screens.auth.AuthView
import com.example.makeyourselfapp.view.screens.goals.GoalsView
import com.example.makeyourselfapp.view.screens.menu.MenuView
import com.example.makeyourselfapp.view.screens.reg.RegView
import com.example.makeyourselfapp.view.screens.splash.Splash
import com.example.makeyourselfapp.view.screens.statistics.StatisticsView

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
    }
}