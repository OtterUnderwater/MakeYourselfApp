package com.example.makeyourselfapp.view.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.view.components.CircleGradient
import com.example.makeyourselfapp.view.components.TextTittlePrimary
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun StatisticsView(controller: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(32.dp))
        TextTittlePrimary("Колесо баланса", Modifier.fillMaxWidth().align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(32.dp))
        Column (modifier = Modifier.fillMaxWidth()
            .background(AppDesign.colors.lightBackground)
            .align(Alignment.CenterHorizontally)
            .padding(24.dp)){
            CircleGradient()
        }
    }
}