package com.example.makeyourselfapp.view.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.models.screens.VMForTask
import com.example.makeyourselfapp.view.components.CheckBoxMenu
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.TextBodyBold
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.screens.dialogWindows.TaskView
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun MenuView(controller: NavHostController, viewModel: MenuViewModel = hiltViewModel()) {
    val state = viewModel.state
    var showDialog by remember { mutableStateOf(false) }
    val listColor = listOf(AppDesign.colors.accent, AppDesign.colors.tertiary,
        AppDesign.colors.secondary, AppDesign.colors.primary)
    var idTask by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.launch()
    }

    if (state.loading.value) {
        CircularProgressCenter()
    } else {
        Column (modifier = Modifier.verticalScroll(rememberScrollState())){
            Spacer(modifier = Modifier.height(24.dp))
            state.listCategories.forEach { category ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(listColor[category.id - 1], RoundedCornerShape(16.dp))
                        .padding(24.dp)
                ) {
                    TextTittle(category.category)
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider( thickness = 2.dp, color = AppDesign.colors.additional)
                    var countTask = 0
                    if (state.listTasks.isNotEmpty()){
                        state.listTasks.forEachIndexed { index, task ->
                            if (task.idCategory == category.id){
                                countTask++
                                Spacer(modifier = Modifier.height(12.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            AppDesign.colors.lightBackground,
                                            RoundedCornerShape(16.dp)
                                        )
                                        .padding(horizontal = 8.dp)
                                ) {
                                    CheckBoxMenu(task.status, listColor[category.id - 1]) {
                                        viewModel.changeStatus(task.id, it)
                                    }
                                    Row (modifier = Modifier
                                        .fillMaxWidth().align(Alignment.CenterVertically)
                                        .clickable {
                                            showDialog = true
                                            idTask = index
                                        }
                                    ){
                                        TextBodyBold(task.nameTask!!, Modifier.align(Alignment.CenterVertically))
                                    }
                                }
                            }
                        }
                    }
                    if (countTask == 0){
                        TextBodyBold("Нет задач", Modifier.padding(top = 12.dp))
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        if (showDialog) {
            TaskView(state.listTasks[idTask], state.listCategories, VMForTask(menuVM = viewModel)){
                showDialog = false
            }
        }
    }
}