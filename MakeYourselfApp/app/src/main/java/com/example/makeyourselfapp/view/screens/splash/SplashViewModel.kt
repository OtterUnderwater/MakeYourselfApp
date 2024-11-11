package com.example.makeyourselfapp.view.screens.splash

import android.content.res.Configuration
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.domain.repository.PrefManager.status
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
                //supabase.auth.currentUserOrNull() != null
                if(status == 0){
                    isVisible.value =  false
                    controller.navigate(RoutesNavigation.AUTH) {
                        popUpTo(RoutesNavigation.SPLASH) {
                            inclusive = true
                        }
                    }
                }
                else {
                    isVisible.value = true
                    controller.navigate(RoutesNavigation.GOALS) {
                        popUpTo(RoutesNavigation.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}