package com.example.makeyourselfapp.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.models.database.Categories
import com.example.makeyourselfapp.models.database.Tasks
import com.example.makeyourselfapp.view.screens.itemGoal.ItemGoalViewModel
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddTaskView(categories: List<Categories>, viewModel: ItemGoalViewModel, switchDialog: () -> Unit) {
    val providerDensity = LocalDensity.current
    var dropdownWidth by remember {  mutableStateOf(0.dp) }
    val listColor = listOf(AppDesign.colors.accent, AppDesign.colors.tertiary,
        AppDesign.colors.secondary, AppDesign.colors.primary)
    var task by remember {  mutableStateOf(Tasks(idCategory = 1, status = false))}
    Dialog(onDismissRequest = {switchDialog()} ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = AppDesign.colors.lightBackground),
        ) {
            Column (modifier = Modifier.fillMaxWidth().padding(24.dp)
                .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp)))
            {
                var expanded by remember { mutableStateOf(false) }
                var selectedOption by remember { mutableStateOf<Categories>(categories.first()) }
                TextTittle("Создание задачи:", Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(20.dp))
                Box {
                    Row (modifier = Modifier.fillMaxWidth()
                        .onGloballyPositioned {
                            dropdownWidth = with(providerDensity) { it.size.width.toDp() }
                        }
                        .background(listColor[task.idCategory - 1], RoundedCornerShape(16.dp))
                        .clickable { expanded = true },
                        horizontalArrangement = Arrangement.Center){
                        TextBodyMedium(selectedOption.category, Modifier.padding(16.dp))
                    }
                    DropdownMenu(
                        expanded = expanded,
                        modifier = Modifier.width(dropdownWidth)
                            .background(listColor[task.idCategory - 1]),
                        onDismissRequest = { expanded = false },
                    ) {
                        categories.forEach {
                            DropdownMenuItem(
                                onClick = {
                                    selectedOption = it
                                    task = task.copy(idCategory = selectedOption.id)
                                },
                                colors = MenuDefaults.itemColors(textColor = AppDesign.colors.textColor),
                                text = { Text(it.category ) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldSmall(task.nameTask,"Наименование задачи") { task = task.copy(nameTask = it)}
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldBig(task.description,"Описание") { task = task.copy(description = it) }
                Spacer(modifier = Modifier.height(20.dp))
                ButtonPrimary("Добавить") {
                    viewModel.addTasks(task)
                    switchDialog()
                }
            }
        }
    }
}

@Composable
fun YesOrNo(controller: NavHostController, switchDialog: () -> Unit) {
    Dialog(onDismissRequest = { switchDialog()} ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = AppDesign.colors.lightBackground),
        ) {
            Column (modifier = Modifier.fillMaxWidth()) {
            TextTittle("Выйти из аккаунта?", Modifier.align(Alignment.CenterHorizontally)
                .padding(vertical = 32.dp))
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Column (modifier = Modifier.weight(0.5f)) {
                    AnswerClickable("Да") {
                        controller.navigate(RoutesNavigation.SPLASH)
                        //currentUser = null
                        switchDialog()
                    }
                }
                Column (modifier = Modifier.weight(0.5f)) {
                    AnswerClickable("Нет") {
                        switchDialog()
                    }
                }
            }
            }
        }
    }
}