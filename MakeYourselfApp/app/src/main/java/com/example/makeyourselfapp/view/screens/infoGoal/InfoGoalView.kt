package com.example.makeyourselfapp.view.screens.infoGoal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres
import com.example.makeyourselfapp.models.screens.StateInfoGoal
import com.example.makeyourselfapp.models.screens.VMForTask
import com.example.makeyourselfapp.view.components.ButtonColorIcon
import com.example.makeyourselfapp.view.components.ButtonPrimary
import com.example.makeyourselfapp.view.components.CheckBoxMenu
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.TextBodyBold
import com.example.makeyourselfapp.view.components.TextBodyMedium
import com.example.makeyourselfapp.view.components.TextFieldBig
import com.example.makeyourselfapp.view.components.TextFieldSmall
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.screens.dialogWindows.AddTaskView
import com.example.makeyourselfapp.view.screens.dialogWindows.TaskView
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun InfoGoalView (controller: NavHostController, goal: Goals, viewModel: InfoGoalViewModel = hiltViewModel()) {
   var state = viewModel.state
   viewModel.setGoals(goal)

   var isVisibleShow by remember { mutableStateOf(true) }

   LaunchedEffect(Unit) {
      viewModel.launch()
   }

   if(state.loading.value) {
      CircularProgressCenter()
   }
   else
   {
      Column (Modifier.verticalScroll(rememberScrollState())){
         Column(modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 0.dp)){
            ButtonPrimary("Назад") {
               viewModel.goToGoals(controller)
            }
         }
         if (isVisibleShow) {
            InfoGoalShow(state, controller, viewModel) { isVisibleShow = false }
         }
         else{
            EditInfoGoal(state, viewModel) { isVisibleShow = true }
         }
      }
   }
}

@Composable
fun InfoGoalShow(state: StateInfoGoal, controller: NavHostController, viewModel: InfoGoalViewModel, isVisibleEdit: () -> Unit)
{
   var showTaskDialog by remember { mutableStateOf(false) }
   var idTask by remember { mutableStateOf(0) }
   var goal by remember { mutableStateOf(state.goal) }
   Column(
      modifier = Modifier
         .fillMaxSize().padding(24.dp)
         .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))) {
      Column (Modifier.fillMaxSize().padding(24.dp)) {

         Row(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            CheckBoxMenu(goal.status, AppDesign.colors.primary) {
               goal = goal.copy(status = it)
               viewModel.changeStatusGoal(goal)
            }
            TextTittle(goal.name!!, Modifier.align(Alignment.CenterVertically))
         }
         Divider( thickness = 2.dp, color = AppDesign.colors.additional)
         TextBodyMedium(goal.description ?: "Описание отсутствует", Modifier.padding(vertical = 24.dp))
         Row (modifier = Modifier.fillMaxWidth()
            .border(2.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))){
            TextBodyMedium("Сфера: ${state.listSpheres.first { it.id == goal.idSphere }.name}",
               Modifier.padding(16.dp))
         }
         Spacer(modifier = Modifier.height(12.dp))
         var showDialog by remember { mutableStateOf(false) }
         ButtonPrimary("Добавить задачу") {
            showDialog = true
         }
         if (showDialog) {
            AddTaskView(state.listCategories, VMForTask(infoGoalVM = viewModel)){
               showDialog = false
            }
         }
         Spacer(modifier = Modifier.height(12.dp))
         Column (Modifier.padding(start = 24.dp)) {
            TextBodyBold("Задачи:")
            if (state.listTasks.isNotEmpty()){
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
                        viewModel.changeStatusTask(task.id, it)
                     }
                     TextBodyMedium(task.nameTask!!, Modifier.align(Alignment.CenterVertically).clickable {
                        showTaskDialog = true
                        idTask = index
                     })
                  }
               }
            }
            else TextBodyMedium("У цели пока нет задач", Modifier.padding(vertical = 12.dp))
         }
         if (showTaskDialog) {
            TaskView(state.listTasks[idTask], state.listCategories, VMForTask(infoGoalVM = viewModel)){
               showTaskDialog = false
            }
         }
         Spacer(modifier = Modifier.height(12.dp))
            Row (modifier = Modifier.fillMaxWidth()){
               Box(modifier = Modifier.weight(0.5f)){
                  ButtonColorIcon(R.drawable.icon_edit, AppDesign.colors.primary){
                     isVisibleEdit()
                  }
               }
               Spacer(modifier = Modifier.width(8.dp))
               Box(modifier = Modifier.weight(0.5f)){
                  ButtonColorIcon(R.drawable.icon_delete, AppDesign.colors.accent){
                     viewModel.deleteGoal(controller)
                  }
               }
         }
      }
   }
}

@Composable
fun EditInfoGoal(state: StateInfoGoal, viewModel: InfoGoalViewModel = hiltViewModel(), isVisibleShow: () -> Unit) {
   val providerDensity = LocalDensity.current //провейдер плотности пикселей
   var dropdownWidth by remember {  mutableStateOf(0.dp) } //размер выпадающего списка под контекст
   var showTaskDialog by remember { mutableStateOf(false) }
   var idTask by remember { mutableStateOf(0) }
   var newGoal by remember { mutableStateOf(state.goal) }
   Column(
      modifier = Modifier
         .fillMaxSize().padding(24.dp)
         .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))) {
      Column (Modifier.fillMaxSize().padding(24.dp)) {
         Row(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            CheckBoxMenu(newGoal.status, AppDesign.colors.primary) {
               newGoal = newGoal.copy(status = it)
            }
            TextFieldSmall(newGoal.name!!,"Наименование цели") {
               newGoal = newGoal.copy(name = it)
            }
         }
         Spacer(modifier = Modifier.height(20.dp))
         Divider( thickness = 2.dp, color = AppDesign.colors.additional)
         Spacer(modifier = Modifier.height(20.dp))
         TextFieldBig(newGoal.description?: "Описание отсутствует","Описание") {
            newGoal = newGoal.copy(description = it)
         }
         Spacer(modifier = Modifier.height(20.dp))
         var expanded by remember { mutableStateOf(false) }
         var selectedOption by remember { mutableStateOf<Spheres>(state.listSpheres.first{it.id == newGoal.idSphere}) }
         Box {
            Row (modifier = Modifier.fillMaxWidth()
               .onGloballyPositioned { dropdownWidth = with(providerDensity) { it.size.width.toDp() } }
               .border(2.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))
               .clickable { expanded = true }){
               TextBodyMedium("Сфера: ${selectedOption?.name ?: "Нет сферы"}", Modifier.padding(16.dp))
            }
            DropdownMenu(
               expanded = expanded,
               modifier = Modifier.width(dropdownWidth)
                  .background(AppDesign.colors.lightBackground),
               onDismissRequest = { expanded = false },
            ) {
               state.listSpheres.forEach { it ->
                  DropdownMenuItem(
                     onClick = {
                        selectedOption = it
                        newGoal = newGoal.copy(idSphere = it.id)
                        expanded = false
                     },
                     colors = MenuDefaults.itemColors(textColor = AppDesign.colors.textColor),
                     text = { Text(it.name) }
                  )
               }
            }
         }
         Spacer(modifier = Modifier.height(12.dp))
         var showDialog by remember { mutableStateOf(false) }
         ButtonPrimary("Добавить задачу") {
            showDialog = true
         }
         if (showDialog) {
            AddTaskView(state.listCategories, VMForTask(infoGoalVM = viewModel)){
               showDialog = false
            }
         }
         Spacer(modifier = Modifier.height(12.dp))
         TextBodyBold("Задачи:")
         if (state.listTasks.isNotEmpty()){
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
                     viewModel.changeStatusTask(task.id, it)
                  }
                  TextBodyMedium(task.nameTask!!, Modifier.align(Alignment.CenterVertically).clickable {
                     showTaskDialog = true
                     idTask = index
                  })
               }
            }
         }
         else TextBodyMedium("У цели пока нет задач", Modifier.padding(vertical = 12.dp))
         if (showTaskDialog) {
            TaskView(state.listTasks[idTask], state.listCategories, VMForTask(infoGoalVM = viewModel)){
               showTaskDialog = false
            }
         }
         Spacer(modifier = Modifier.height(24.dp))
         ButtonPrimary("Сохранить изменения", newGoal.name != "") {
            viewModel.changeGoal(newGoal)
            isVisibleShow()
         }
      }
   }
}
