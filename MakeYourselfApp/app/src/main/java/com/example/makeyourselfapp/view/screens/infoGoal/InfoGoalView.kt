package com.example.makeyourselfapp.view.screens.infoGoal

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.screens.StateInfoGoal
import com.example.makeyourselfapp.view.components.ButtonColorIcon
import com.example.makeyourselfapp.view.components.CheckBoxMenu
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.TextBodyBold
import com.example.makeyourselfapp.view.components.TextBodyMedium
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun InfoGoalView (controller: NavHostController, goal: Goals, viewModel: InfoGoalViewModel = hiltViewModel()) {
   val state = viewModel.state.collectAsState()
   viewModel.setState(state.value.copy(goal = goal))

   var isVisibleShow by remember { mutableStateOf(true) }

   LaunchedEffect(Unit) {
      viewModel.launch()
   }

   if(state.value.loading) {
      CircularProgressCenter()
   }
   else
   {
      Column (Modifier.verticalScroll(rememberScrollState())){
         if (isVisibleShow) {
            InfoGoalShow(state, controller, viewModel) { isVisibleShow = false }
         }
         else{
            //TaskEdit(state, viewModel) { isVisibleShow = true }
         }
      }
   }
}

@Composable
fun InfoGoalShow(state: State<StateInfoGoal>, controller: NavHostController, viewModel: InfoGoalViewModel, isVisibleEdit: () -> Unit)
{
   Column(
      modifier = Modifier
         .fillMaxSize().padding(24.dp)
         .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))) {
      Column (Modifier.fillMaxSize().padding(24.dp)) {

         Row(Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            CheckBoxMenu(state.value.goal.status, AppDesign.colors.primary) {
               viewModel.changeStatusGoal(it)
            }
            TextTittle(state.value.goal.name!!, Modifier.align(Alignment.CenterVertically))
         }
         Divider( thickness = 2.dp, color = AppDesign.colors.additional)
         TextBodyMedium(state.value.goal.description ?: "Описание отсутствует", Modifier.padding(vertical = 12.dp))
         TextBodyBold("Задачи:")
         var idTask = 0
         if (state.value.listTasks.isNotEmpty()){
            state.value.listTasks.forEachIndexed { index, task ->
               Spacer(modifier = Modifier.height(12.dp))
               Row(
                  modifier = Modifier
                     .fillMaxWidth()
                     .padding(horizontal = 8.dp)
               ) {
                  CheckBoxMenu(task.status, AppDesign.colors.primary) {
                     viewModel.changeStatusTask(task.id, it)
                  }
                  Row (modifier = Modifier
                     .fillMaxWidth().align(Alignment.CenterVertically)
                     .clickable {
                        idTask = index
                     }
                  ){
                     TextBodyBold(task.nameTask!!, Modifier.align(Alignment.CenterVertically))
                  }
               }
            }
         }
//         TextBodyBold(state.listSpheres.first { it.id == state.goal.idSphere}.name)



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
fun EditInfoGoal (controller: NavHostController, goal: Goals, viewModel: InfoGoalViewModel = hiltViewModel()) {
   val state = viewModel.state

   if(false) {
      CircularProgressCenter()
   }
   else
   {
      Column (Modifier.verticalScroll(rememberScrollState())){
         Column(
            modifier = Modifier
               .fillMaxSize()
               .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(24.dp))
            TextTittle(goal.name!!)


            //  Spacer(modifier = Modifier.height(20.dp))
            //                TextFieldSmall(state.goal.name!!,"Наименование цели")
            //                { viewModel.setGoals(state.goal.copy(name = it)) }
            //                Spacer(modifier = Modifier.height(20.dp))
            //                Divider( thickness = 2.dp, color = AppDesign.colors.additional)
            //                Spacer(modifier = Modifier.height(20.dp))
            //                TextFieldBig(state.goal.description!!,"Описание")
            //                { viewModel.setGoals(state.goal.copy(description = it)) }
            //                Spacer(modifier = Modifier.height(20.dp))
            //                var expanded by remember { mutableStateOf(false) }
            //                state.goal = state.goal.copy(idSphere = state.listSpheres.first().id)
            //                var selectedOption by remember { mutableStateOf<Spheres?>(state.listSpheres.firstOrNull()) }
            //                Box {
            //                    Row (modifier = Modifier.fillMaxWidth()
            //                        .onGloballyPositioned { dropdownWidth = with(providerDensity) { it.size.width.toDp() } }
            //                        .border(2.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))
            //                        .clickable { expanded = true }){
            //                        TextBodyMedium("Сфера: ${selectedOption?.name ?: "Нет сферы"}", Modifier.padding(16.dp))
            //                    }
            //                    DropdownMenu(
            //                        expanded = expanded,
            //                        modifier = Modifier.width(dropdownWidth)
            //                            .background(AppDesign.colors.lightBackground),
            //                                onDismissRequest = { expanded = false },
            //                            ) {
            //                                state.listSpheres.forEach { it ->
            //                                    DropdownMenuItem(
            //                                        onClick = {
            //                                            selectedOption = it
            //                                            viewModel.setGoals(state.goal.copy(idSphere = it.id))
            //                                            expanded = false
            //                                        },
            //                                        colors = MenuDefaults.itemColors(textColor = AppDesign.colors.textColor),
            //                                        text = { Text(it.name ) }
            //                                    )
            //                                }
            //                            }
            //                }
            //                Spacer(modifier = Modifier.height(24.dp))
            //                var showDialog by remember { mutableStateOf(false) }
            //                ButtonPrimary("Добавить задачу") {
            //                    showDialog = true
            //                }
            //                if (showDialog) {
            //                    AddTaskView(state.listCategories, viewModel){
            //                        showDialog = false
            //                    }
            //                }
            //                state.listTasks.forEach { task ->
            //                        Spacer(modifier = Modifier.height(12.dp))
            //                        Row(
            //                            modifier = Modifier.fillMaxWidth()
            //                                .background(AppDesign.colors.lightBackground,
            //                                    RoundedCornerShape(16.dp))
            //                                .padding(horizontal = 8.dp)
            //                                .border(2.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))
            //                        ) {
            //                            CheckBoxMenu(task.status, AppDesign.colors.primary) {
            //                                viewModel.changeStatus(task.id, it)
            //                            }
            //                            TextBodyMedium(task.nameTask!!, Modifier.align(Alignment.CenterVertically))
            //                        }
            //                }
            //                Spacer(modifier = Modifier.height(24.dp))
            //                ButtonPrimary("Создать цель", state.goal.name != "") {
            //                    state.goal.idUser = currentUser!!
            //                    viewModel.createGoal(controller)
            //                }
            //                Spacer(modifier = Modifier.height(24.dp))


            Row (modifier = Modifier.fillMaxWidth()){
               Box(modifier = Modifier.weight(0.5f)){
                  ButtonColorIcon(R.drawable.icon_edit, AppDesign.colors.primary){
                     //isVisibleEdit()
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
}
