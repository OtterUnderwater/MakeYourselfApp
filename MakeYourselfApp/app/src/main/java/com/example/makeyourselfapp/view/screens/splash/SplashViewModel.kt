package com.example.makeyourselfapp.view.screens.splash

import android.content.res.Configuration
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    fun launch(controller: NavHostController, isVisible: MutableState<Boolean>, configuration: Configuration) {
        viewModelScope.launch {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                delay(2000L)

                controller.navigate(RoutesNavigation.AUTH) {
                    isVisible.value = false
                    popUpTo(RoutesNavigation.SPLASH) {
                        inclusive = true
                    }
                }

                /*if(service.userIsAuth()){
                controller.navigate(RoutesNavigation.GOALS) {
                    isVisible.value = true
                    popUpTo(RoutesNavigation.SPLASH) {
                        inclusive = true
                    }
                }}
                else {
                isVisible.value = false
                    controller.navigate(RoutesNavigation.AUTH) {
                        popUpTo(NavigationRoutes.SPLASH) {
                            inclusive = true
                        }
                    }
                }*/
            }
        }
    }
}