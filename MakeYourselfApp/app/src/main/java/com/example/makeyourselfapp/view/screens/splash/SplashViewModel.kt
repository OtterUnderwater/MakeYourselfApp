package com.example.makeyourselfapp.view.screens.splash

import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    fun launch(controller: NavHostController, configuration: Configuration) {
        viewModelScope.launch {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                delay(2000L)
                /*if(service.userIsAuth()){
                    controller.navigate(NavigationRoutes.HOME) {
                        popUpTo(NavigationRoutes.SPLASH) {
                            inclusive = true
                        }
                    }
                } else {
                    controller.navigate(NavigationRoutes.AUTH) {
                        popUpTo(NavigationRoutes.SPLASH) {
                            inclusive = true
                        }
                    }
                }*/
            }
        }
    }
}