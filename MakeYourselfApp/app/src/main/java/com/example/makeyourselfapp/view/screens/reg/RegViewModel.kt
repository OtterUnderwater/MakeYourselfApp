package com.example.makeyourselfapp.view.screens.reg

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.models.screens.StateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegViewModel @Inject constructor() : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    private val _user = mutableStateOf(StateUser())
    val user: StateUser get() = _user.value

    fun setUser(newUser: StateUser) {
        _user.value = newUser
    }
    fun goAuth(controller: NavController) {
        controller.navigate(RoutesNavigation.AUTH)
    }

    fun signUp(controller: NavController) {
    }
}