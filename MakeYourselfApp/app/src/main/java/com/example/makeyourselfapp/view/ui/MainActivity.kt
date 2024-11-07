package com.example.makeyourselfapp.view.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.makeyourselfapp.domain.navigation.Navigation
import com.example.makeyourselfapp.models.theme.CurrentTheme
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.screens.bottombar.BottomBarCustom
import com.example.makeyourselfapp.view.screens.splash.Splash
import com.example.makeyourselfapp.view.screens.topbar.TopBarCustom
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
            val controller = rememberNavController()
            val theme = remember { mutableStateOf(CurrentTheme(0, ListColorTheme[0])) }
            val isVisibleBar = remember { mutableStateOf(false) }
            MakeYourselfAppTheme ( currentTheme = theme.value.theme)
            {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppDesign.colors.background),
                    topBar = { if (isVisibleBar.value) TopBarCustom(controller, theme) },
                    bottomBar = { if (isVisibleBar.value) BottomBarCustom(controller, theme) }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding).background(AppDesign.colors.lightBackground).fillMaxSize())
                    {
                        Navigation(controller, isVisibleBar)
                    }
                }
            }
        }
    }
}