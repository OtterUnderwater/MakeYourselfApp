package com.example.makeyourselfapp.view.screens.bottombar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.models.screens.BottomNavItem

@Composable
fun BottomBarCustom(controller: NavHostController, modifier: Modifier = Modifier){
    NavigationBar(
        modifier = Modifier.padding(10.dp))
    {
        controller.navigate(BottomItems.NavItems[0])
    }
}


object BottomItems {
    val NavItems = listOf(
        BottomNavItem(
            icon = R.drawable.icon_statistics,
            route = RoutesNavigation.STATISTICS
        ),
        BottomNavItem(
            icon = R.drawable.icon_goals,
            route = RoutesNavigation.GOALS
        ),
        BottomNavItem(
            icon = R.drawable.icon_menu,
            route = RoutesNavigation.MENU
        )
    )
}