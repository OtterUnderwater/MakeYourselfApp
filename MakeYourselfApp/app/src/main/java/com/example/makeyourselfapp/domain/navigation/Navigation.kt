package com.example.makeyourselfapp.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.makeyourselfapp.view.screens.splash.Splash

@Composable
fun Navigation(controller: NavHostController){
    NavHost(
        navController = controller,
        startDestination = RoutesNavigation.SPLASH
    ) {
        composable(RoutesNavigation.SPLASH) {
            Splash(controller)
        }

    }
}