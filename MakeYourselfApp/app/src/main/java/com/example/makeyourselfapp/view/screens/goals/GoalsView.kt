package com.example.makeyourselfapp.view.screens.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.view.components.ButtonPrimary
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.TextBodyBold
import com.example.makeyourselfapp.view.components.TextBodyMedium
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.screens.reg.RegViewModel
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun GoalsView(controller: NavHostController, viewModel: GoalsViewModel = hiltViewModel()) {
    val state = viewModel.state
    LaunchedEffect(Unit) {
        viewModel.launch()
    }

    if (state.loading.value) {
        CircularProgressCenter()
    }
    else {
        Column (Modifier.verticalScroll(rememberScrollState())){
            Column(
                modifier = Modifier.padding(28.dp, 32.dp)
            ) {
                ButtonPrimary("Создать новую цель", true) { viewModel.createItem(controller) }
                TextTittle("Не выполненые:", Modifier.padding(4.dp, 20.dp))
                if (state.notCompletedGoals.isNotEmpty()) {
                    state.notCompletedGoals.forEach {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .background(AppDesign.colors.primary, RoundedCornerShape(16.dp))
                                .clickable { viewModel.openItem(controller, it) }
                        ) {
                            TextBodyBold(it.name!!, Modifier.padding(16.dp))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                } else TextBodyMedium("Нет задач", Modifier.padding(start = 4.dp, bottom = 20.dp))
                TextTittle("Завершенные:", Modifier.padding(4.dp, 8.dp, 4.dp, 20.dp))
                if (state.completedGoals.isNotEmpty()) {
                    state.completedGoals.forEach {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
                                .clickable { viewModel.openItem(controller, it) }
                        ) {
                            TextBodyBold(it.name!!, Modifier.padding(16.dp))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                } else TextBodyMedium("Нет задач", Modifier.padding(start = 4.dp))
            }
        }
    }
}