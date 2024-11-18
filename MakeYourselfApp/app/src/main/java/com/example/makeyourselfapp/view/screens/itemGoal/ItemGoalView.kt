package com.example.makeyourselfapp.view.screens.itemGoal

import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.models.database.Spheres
import com.example.makeyourselfapp.view.components.TextBodyBold
import com.example.makeyourselfapp.view.components.TextFieldBig
import com.example.makeyourselfapp.view.components.TextFieldSmall
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun ItemGoalView(controller: NavHostController, viewModel: ItemGoalViewModel = hiltViewModel())
{
    val providerDensity = LocalDensity.current //провейдер плотности пикселей
    var dropdownWidth by remember {  mutableStateOf(0.dp) } //размер выпадающего списка под контекст
    val state = viewModel.state
    LaunchedEffect(Unit) { viewModel.launch() }
    Spacer(modifier = Modifier.height(24.dp))
    if (state.loading) {
        Column(modifier = Modifier.fillMaxWidth()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = AppDesign.colors.primary)
        }
    } else {
        Box(
            modifier = Modifier.fillMaxWidth().padding(12.dp)
                .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
        ) {
            Column (modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp)))
            {
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldSmall(state.goals.name!!,"Наименование цели")
                { viewModel.setGoals(state.goals.copy(name = it)) }
                Divider( thickness = 2.dp, color = AppDesign.colors.lightBackground)
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldBig(state.goals.description!!,"Описание")
                { viewModel.setGoals(state.goals.copy(description = it)) }
                Spacer(modifier = Modifier.height(20.dp))
                var expanded by remember { mutableStateOf(false) }
                var selectedOption by remember { mutableStateOf<Spheres?>(state.listSphere.firstOrNull()) }
                Box {
                    Row (modifier = Modifier.fillMaxWidth()
                        .onGloballyPositioned { dropdownWidth = with(providerDensity) { it.size.width.toDp() } }
                        .border(2.dp, AppDesign.colors.primary, RoundedCornerShape(16.dp))
                        .clickable { expanded = true }){
                        TextBodyBold("Сфера: ${selectedOption?.name ?: "Нет сферы"}", Modifier.padding(16.dp))
                    }
                    DropdownMenu(
                        expanded = expanded,
                        modifier = Modifier.width(dropdownWidth)
                            .background(AppDesign.colors.lightBackground),
                                onDismissRequest = { expanded = false },
                            ) {
                                state.listSphere.forEach { it ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedOption = it
                                            viewModel.setGoals(state.goals.copy(idSphere = it.id))
                                        },
                                        colors = MenuDefaults.itemColors(textColor = AppDesign.colors.textColor),
                                        text = { Text(it.name ) }
                                    )
                                }
                            }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
        //Добавить задачу
}
