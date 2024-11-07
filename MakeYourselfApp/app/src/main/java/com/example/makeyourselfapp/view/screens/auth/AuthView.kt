package com.example.makeyourselfapp.view.screens.auth

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.makeyourselfapp.view.components.ButtonPrimary
import com.example.makeyourselfapp.view.components.TextClickable
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun AuthView(controller: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    viewModel.context = LocalContext.current
    val stateButton = (viewModel.user.password != "" && viewModel.user.login != "")
    Column(
        modifier = Modifier.fillMaxSize().background(AppDesign.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                TextTittle("Авторизация")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Spacer(modifier = Modifier.height(30.dp))
            ButtonPrimary("Войти", stateButton) { viewModel.signIn(controller) }
            Spacer(modifier = Modifier.height(80.dp))
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                TextTittle("Нет аккаунта?")
                Spacer(modifier = Modifier.width(8.dp))
                TextClickable("Зарегистрируйтесь") { viewModel.goReg(controller) }
            }
        }
    }
}