package com.example.makeyourselfapp.view.screens.dialogWindows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.view.components.AnswerClickable
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun YesOrNo(controller: NavHostController, switchDialog: () -> Unit) {
    Dialog(onDismissRequest = { switchDialog()} ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = AppDesign.colors.lightBackground),
        ) {
            Column (modifier = Modifier.fillMaxWidth()) {
                TextTittle("Выйти из аккаунта?",
                    Modifier
                        .align(Alignment.CenterHorizontally)
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
