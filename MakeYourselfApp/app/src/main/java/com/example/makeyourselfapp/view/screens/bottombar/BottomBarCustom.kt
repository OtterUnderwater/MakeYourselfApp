package com.example.makeyourselfapp.view.screens.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.models.screens.BottomNavItem
import com.example.makeyourselfapp.models.theme.CurrentTheme
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun BottomBarCustom(controller: NavHostController, theme: MutableState<CurrentTheme>,
                    viewModel: BottomBarCustomViewModel = hiltViewModel()){
    Box( modifier = Modifier.height(90.dp).fillMaxWidth()
            .background(AppDesign.colors.background).padding(bottom = 20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Divider(thickness = 2.dp, color = AppDesign.colors.lightBackground)
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround)
        {
            BottomItems.NavItems.forEach { navItem ->
                IconButton( onClick = { controller.navigate(navItem.route) } )
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navItem.icon),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = AppDesign.colors.textColor
                    )
                }
            }
        }
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