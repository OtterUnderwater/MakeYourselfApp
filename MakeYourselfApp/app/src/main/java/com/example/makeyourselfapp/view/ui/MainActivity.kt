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
import com.example.makeyourselfapp.model.theme.CurrentTheme
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
            val state = remember { mutableStateOf(CurrentTheme(0, ListColorTheme[0])) }
            MakeYourselfAppTheme ( currentTheme = state.value.theme)
            {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(AppDesign.colors.background),
                    topBar = {
                        TopBarCustom(controller)
                    /*if (isBottomBarVisible.value) {
                        BottomBar(
                            navController = controller,
                        )
                    }*/
                    },
                    bottomBar = {
                        /*if (isBottomBarVisible.value) {
                            BottomBar(
                                navController = controller,
                            )
                        }*/
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding))
                    {
                        //MainContent(state)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent(state: MutableState<CurrentTheme>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Switch the theme
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = AppDesign.colors.accent,
                disabledContainerColor = AppDesign.colors.accent
            ),
            onClick = {
                val nextIndex = (state.value.index + 1) % 5
                state.value = CurrentTheme(nextIndex, ListColorTheme[nextIndex])
        }) {
            Text("Switch Theme")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = AppDesign.colors.primary,
                disabledContainerColor = AppDesign.colors.primary
            ),
            onClick = {}) {
            Text("Theme")
        }
    }
}