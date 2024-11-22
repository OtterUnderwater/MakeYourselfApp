package com.example.makeyourselfapp.view.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.view.components.CheckBoxMenu
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.TextBodyBold
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun MenuView(controller: NavHostController, viewModel: MenuViewModel = hiltViewModel()) {
    val state = viewModel.state
    LaunchedEffect(Unit) { viewModel.launch() }
    val listColor = listOf(AppDesign.colors.accent, AppDesign.colors.tertiary,
        AppDesign.colors.secondary, AppDesign.colors.primary)
    if (state.loading) {
        CircularProgressCenter()
    } else {
        Column{
            Spacer(modifier = Modifier.height(24.dp))
        state.listCategories.forEach { category ->
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                    .background(listColor[category.id - 1], RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                TextTittle(category.category, null)
                Spacer(modifier = Modifier.height(12.dp))
                if (state.listTasks.count() != 0){
                    Divider( thickness = 2.dp, color = AppDesign.colors.additional)
                    state.listTasks.forEach { task ->
                        if (task.idCategory == category.id){
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .background(AppDesign.colors.lightBackground,
                                        RoundedCornerShape(16.dp))
                                    .padding(horizontal = 8.dp)
                            ) {
                                CheckBoxMenu(task.status, listColor[category.id - 1]) {
                                    viewModel.changeStatus(task.id, it)
                                }
                                TextBodyBold(task.nameTask, Modifier.align(Alignment.CenterVertically))
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
    } }
}}