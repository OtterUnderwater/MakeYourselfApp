package com.example.makeyourselfapp.view.screens.reg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.makeyourselfapp.view.components.ButtonPrimary
import com.example.makeyourselfapp.view.components.TextClickable
import com.example.makeyourselfapp.view.components.TextFieldBase
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.components.TextTittlePrimary
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun RegView (controller: NavController, viewModel: RegViewModel = hiltViewModel()){
    viewModel.context = LocalContext.current
    val stateButton = (viewModel.user.password != "" && viewModel.user.login != "")
    Column(
        modifier = Modifier.fillMaxSize().background(AppDesign.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                TextTittlePrimary("Регистрация")
            }
            Spacer(modifier = Modifier.height(28.dp))
            TextFieldBase(viewModel.user.name, "Имя")
            { viewModel.setUser(viewModel.user.copy(name = it))}
            Spacer(modifier = Modifier.height(28.dp))
            TextFieldBase(viewModel.user.login, "Логин" )
            { viewModel.setUser(viewModel.user.copy(login = it))}
            Spacer(modifier = Modifier.height(28.dp))
            TextFieldBase(viewModel.user.password, "Пароль")
            { viewModel.setUser(viewModel.user.copy(password = it))}
            Spacer(modifier = Modifier.height(28.dp))
            TextFieldBase(viewModel.user.twoPassword, "Повторите пароль")
            { viewModel.setUser(viewModel.user.copy(twoPassword = it))}
            Spacer(modifier = Modifier.height(28.dp))
            ButtonPrimary("Зарегистрировать", stateButton) {
                viewModel.signUp(controller)
            }
            Spacer(modifier = Modifier.height(80.dp))
            TextTittle("Есть аккаунт?", Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(8.dp))
            TextClickable("Войти", Modifier.align(Alignment.CenterHorizontally)) {
                viewModel.goAuth(controller)
            }
        }
    }
}