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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.screens.reg.RegViewModel
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun GoalsView(controller: NavHostController, viewModel: GoalsViewModel = hiltViewModel()) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.launch()
    }
    Column(modifier = Modifier.padding(horizontal = 24.dp).padding(vertical = 30.dp)) {
        state.completedGoals.forEach { it ->
            Box(
                modifier = Modifier
                    .border(4.dp, AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
            ) {
                TextTittle(it.name)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
//    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
//        .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Center) {
//        Column(modifier = Modifier.padding(horizontal = 24.dp).padding(vertical = 30.dp)) {
//            state.notCompletedGoals.forEach { it ->
//                Box(
//                    modifier = Modifier
//                        .border(4.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))
//                        .clickable {
//                            //OBJECT.idRole = context.idRole (Элемент)
//                            //viewModel.openItem(controller)
//                        }
//                ) {
//                    TextTittle(it.name)
//                }
//                Spacer(modifier = Modifier.height(12.dp))
//            }
//        }
//        Column(modifier = Modifier.padding(horizontal = 24.dp).padding(vertical = 30.dp)) {
//            state.completedGoals.forEach { it ->
//                Box(
//                    modifier = Modifier
//                        .border(4.dp, AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
//                ) {
//                    TextTittle(it.name)
//                }
//                Spacer(modifier = Modifier.height(12.dp))
//            }
//        }
//    }
}