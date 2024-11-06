package com.example.makeyourselfapp.view.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.R

@Composable
fun Splash(controller: NavHostController, isVisible: MutableState<Boolean>, viewModel: SplashViewModel = hiltViewModel()){
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        viewModel.launch(controller, isVisible, configuration)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.splash_screen),
            contentDescription = "",
            Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}