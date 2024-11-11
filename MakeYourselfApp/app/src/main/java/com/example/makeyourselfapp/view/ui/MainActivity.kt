package com.example.makeyourselfapp.view.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.makeyourselfapp.domain.navigation.Navigation
import com.example.makeyourselfapp.domain.repository.PrefManager
import com.example.makeyourselfapp.models.theme.CurrentTheme
import com.example.makeyourselfapp.view.panels.bottombar.BottomBarCustom
import com.example.makeyourselfapp.view.panels.topbar.TopBarCustom
import com.example.makeyourselfapp.view.ui.theme.MakeYourselfAppTheme
import com.example.makeyourselfapp.view.ui.theme.AppDesign
import com.example.makeyourselfapp.view.ui.theme.ListColorTheme
import dagger.hilt.android.AndroidEntryPoint

// Aннотация точки входа в проект для hilt
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //цвета нижней и верхней панели
            window.statusBarColor = AppDesign.colors.lightBackground.toArgb()
            window.navigationBarColor = AppDesign.colors.lightBackground.toArgb()
            PrefManager.init(LocalContext.current)
            val controller = rememberNavController()
            val theme = remember { mutableStateOf(CurrentTheme(0, ListColorTheme[0])) }
            val isVisibleBar = remember { mutableStateOf(false) }
            MakeYourselfAppTheme ( currentTheme = theme.value.theme)
            {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(AppDesign.colors.background),
                    topBar = { if (isVisibleBar.value) TopBarCustom(controller, theme) },
                    bottomBar = { if (isVisibleBar.value) BottomBarCustom(controller) }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).background(AppDesign.colors.background).fillMaxSize())
                    {
                        Navigation(controller, isVisibleBar)
                    }
                }
            }
        }
    }
}