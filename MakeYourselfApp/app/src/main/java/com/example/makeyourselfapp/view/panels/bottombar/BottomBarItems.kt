package com.example.makeyourselfapp.view.panels.bottombar

import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation

sealed class BottomBarItems(
    val route: String,
    val resourceId: Int? = null
) {
    data object Statistics : BottomBarItems(
        route = RoutesNavigation.STATISTICS,
        resourceId = R.drawable.icon_statistics
    )
    data object Goals : BottomBarItems(
        route = RoutesNavigation.GOALS,
        resourceId = R.drawable.icon_goals
    )
    data object Menu : BottomBarItems(
        route = RoutesNavigation.MENU,
        resourceId = R.drawable.icon_menu
    )
}

val ScreenItems = listOf(BottomBarItems.Statistics, BottomBarItems.Goals, BottomBarItems.Menu)