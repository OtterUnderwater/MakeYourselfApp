package com.example.makeyourselfapp.view.panels.bottombar

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
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun BottomBarCustom(controller: NavHostController) {
    Box(
        modifier = Modifier.height(120.dp).fillMaxWidth()
            .background(AppDesign.colors.lightBackground).padding(bottom = 20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            ScreenItems.forEach { screen ->
                var selectedColor = Color.Transparent
                if (currentRoute == screen.route) {
                    selectedColor = AppDesign.colors.primary
                }
                IconButton(
                    colors = IconButtonColors(
                        containerColor = selectedColor,
                        contentColor = AppDesign.colors.textColor,
                        disabledContainerColor = AppDesign.colors.primary,
                        disabledContentColor = AppDesign.colors.textColor
                    ),
                    onClick = {
                        if (currentRoute != screen.route) {
                            controller.navigate(screen.route) {
                                currentRoute?.let {
                                    popUpTo(it) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                )
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.resourceId!!),
                        modifier = Modifier.size(24.dp),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}