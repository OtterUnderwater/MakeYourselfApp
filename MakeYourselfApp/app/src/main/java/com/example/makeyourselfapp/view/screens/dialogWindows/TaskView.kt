package com.example.makeyourselfapp.view.screens.dialogWindows

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.models.database.Categories
import com.example.makeyourselfapp.models.database.Tasks
import com.example.makeyourselfapp.view.components.ButtonColorIcon
import com.example.makeyourselfapp.view.components.ButtonPrimary
import com.example.makeyourselfapp.view.components.TextBodyMedium
import com.example.makeyourselfapp.view.components.TextFieldBig
import com.example.makeyourselfapp.view.components.TextFieldSmall
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.screens.menu.MenuViewModel
import com.example.makeyourselfapp.view.ui.theme.AppDesign


@Composable
fun TaskView(task: Tasks, categories: List<Categories>, viewModel: MenuViewModel, switchDialog: () -> Unit) {
    val listColor = listOf(
        AppDesign.colors.accent, AppDesign.colors.tertiary,
        AppDesign.colors.secondary, AppDesign.colors.primary)
    var isVisibleShow by remember { mutableStateOf(true) }
    Dialog(onDismissRequest = {switchDialog()} ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = AppDesign.colors.lightBackground),
        ) {
            if (isVisibleShow) {
                TaskShow(task, listColor, categories, viewModel, switchDialog) { isVisibleShow = false }
            }
            else{
                TaskEdit(task, listColor, categories, viewModel) { isVisibleShow = true }
            }
        }
    }
}

@Composable
fun TaskShow(task: Tasks,
             listColor: List<Color>,
             categories: List<Categories>,
             viewModel: MenuViewModel,
             switchDialog: () -> Unit,
             isVisibleEdit: () -> Unit) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)
        .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
        .verticalScroll(rememberScrollState()))
    {
        TextTittle(task.nameTask!!, Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(8.dp))
        Divider( thickness = 2.dp, color = AppDesign.colors.additional)
        TextBodyMedium(task.description ?: "Описание отсутствует", Modifier.padding(vertical = 20.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .background(listColor[task.idCategory - 1], RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.Center){
            TextBodyMedium(categories.first { it.id == task.idCategory }.category, Modifier.padding(16.dp))
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
                    viewModel.deleteTask(task)
                    switchDialog()
                }
            }
        }
    }
}

@Composable
fun TaskEdit(task: Tasks,
             listColor: List<Color>,
             categories: List<Categories>,
             viewModel: MenuViewModel,
             isVisibleEdit: () -> Unit) {
    val providerDensity = LocalDensity.current
    var dropdownWidth by remember {  mutableStateOf(0.dp) }
    var newTask by remember {  mutableStateOf(task) }
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)
        .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
        .verticalScroll(rememberScrollState()))
    {
        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf<Categories>(categories.first { it.id == task.idCategory }) }
        Spacer(modifier = Modifier.height(20.dp))
        TextBodyMedium("Наименование:", Modifier.padding(top = 4.dp))
        TextFieldSmall(newTask.nameTask!!,"Наименование задачи") {
            newTask = newTask.copy(nameTask = it)
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextBodyMedium("Описание:", Modifier.padding(top = 4.dp))
        TextFieldBig(newTask.description!!, "Описание") {
            newTask = newTask.copy(description = it)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box {
            Row (modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    dropdownWidth = with(providerDensity) { it.size.width.toDp() }
                }
                .background(listColor[newTask.idCategory - 1], RoundedCornerShape(16.dp))
                .clickable { expanded = true },
                horizontalArrangement = Arrangement.Center){
                TextBodyMedium(selectedOption.category, Modifier.padding(16.dp))
            }
            DropdownMenu(
                expanded = expanded,
                modifier = Modifier
                    .width(dropdownWidth)
                    .background(listColor[newTask.idCategory - 1]),
                onDismissRequest = { expanded = false },
            ) {
                categories.forEach {
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = it
                            newTask = newTask.copy(idCategory = selectedOption.id)
                        },
                        colors = MenuDefaults.itemColors(textColor = AppDesign.colors.textColor),
                        text = { Text(it.category ) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ButtonPrimary("Сохранить",newTask.nameTask != "" ){
            viewModel.changeTask(newTask)
            isVisibleEdit()
        }
    }
}