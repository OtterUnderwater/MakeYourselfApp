package com.example.makeyourselfapp.view.screens.itemGoal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.repository.PrefManager.currentUser
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres
import com.example.makeyourselfapp.models.screens.VMForTask
import com.example.makeyourselfapp.view.components.ButtonPrimary
import com.example.makeyourselfapp.view.components.CheckBoxMenu
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.TextBodyMedium
import com.example.makeyourselfapp.view.components.TextFieldBig
import com.example.makeyourselfapp.view.components.TextFieldSmall
import com.example.makeyourselfapp.view.screens.dialogWindows.AddTaskView
import com.example.makeyourselfapp.view.screens.dialogWindows.TaskView
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun ItemGoalView(controller: NavHostController, viewModel: ItemGoalViewModel = hiltViewModel())
{
    val providerDensity = LocalDensity.current //провейдер плотности пикселей
    var dropdownWidth by remember {  mutableStateOf(0.dp) } //размер выпадающего списка под контекст
    val state = viewModel.state
    var newGoal by remember { mutableStateOf(Goals()) }
    LaunchedEffect(Unit) { viewModel.launch() }
    Spacer(modifier = Modifier.height(24.dp))
    if (state.loading.value) {
        CircularProgressCenter()
    } else {
        Box(
            modifier = Modifier.fillMaxWidth().padding(12.dp)
                .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
        ) {
            Column (modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp)))
            {
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldSmall(newGoal.name!!,"Наименование цели") {
                    newGoal = newGoal.copy(name = it)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider( thickness = 2.dp, color = AppDesign.colors.additional)
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldBig(newGoal.description!!,"Описание") {
                    newGoal = newGoal.copy(description = it)
                }
                Spacer(modifier = Modifier.height(20.dp))
                var expanded by remember { mutableStateOf(false) }
                newGoal = newGoal.copy(idSphere = state.listSpheres.first().id)
                var selectedOption by remember { mutableStateOf(state.listSpheres.first()) }
                Box {
                    Row (modifier = Modifier.fillMaxWidth()
                        .onGloballyPositioned { dropdownWidth = with(providerDensity) { it.size.width.toDp() } }
                        .border(2.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))
                        .clickable { expanded = true }){
                        TextBodyMedium("Сфера: ${selectedOption.name}", Modifier.padding(16.dp))
                    }
                    DropdownMenu(
                        expanded = expanded,
                        modifier = Modifier.width(dropdownWidth)
                            .background(AppDesign.colors.lightBackground),
                                onDismissRequest = { expanded = false },
                            ) {
                                state.listSpheres.forEach {
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedOption = it
                                            expanded = false
                                        },
                                        colors = MenuDefaults.itemColors(textColor = AppDesign.colors.textColor),
                                        text = { Text(it.name ) }
                                    )
                                }
                            }
                }
                Spacer(modifier = Modifier.height(24.dp))
                var showDialog by remember { mutableStateOf(false) }
                ButtonPrimary("Добавить задачу") {
                    showDialog = true
                }
                if (showDialog) {
                    AddTaskView(state.listCategories, VMForTask(itemGoalVM = viewModel)){
                        showDialog = false
                    }
                }
                var showTaskDialog by remember { mutableStateOf(false) }
                var idTask by remember { mutableStateOf(0) }
                state.listTasks.forEachIndexed { index, task ->
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(AppDesign.colors.lightBackground,
                                    RoundedCornerShape(16.dp))
                                .padding(horizontal = 8.dp)
                                .border(2.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))
                        ) {
                            CheckBoxMenu(task.status, AppDesign.colors.primary) {
                                viewModel.changeStatus(task.id, it)
                            }
                            TextBodyMedium(task.nameTask!!, Modifier.align(Alignment.CenterVertically).clickable {
                                showTaskDialog = true
                                idTask = index
                            })
                        }
                }
                Spacer(modifier = Modifier.height(24.dp))
                ButtonPrimary("Создать цель", newGoal.name != "") {
                    newGoal = newGoal.copy(idUser = currentUser!!, idSphere = selectedOption.id)
                    viewModel.createGoal(controller, newGoal)
                }
                Spacer(modifier = Modifier.height(24.dp))
                if (showTaskDialog) {
                    TaskView(state.listTasks[idTask], state.listCategories, VMForTask(itemGoalVM = viewModel)){
                        showTaskDialog = false
                    }
                }
            }
        }
    }
}
