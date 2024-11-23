package com.example.makeyourselfapp.view.screens.auth

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.makeyourselfapp.domain.Constants
import com.example.makeyourselfapp.domain.Constants.supabase
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.domain.repository.PrefManager.currentUser
import com.example.makeyourselfapp.models.screens.StateMenu
import com.example.makeyourselfapp.models.screens.StateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    private var _user by mutableStateOf(StateUser())
    val user: StateUser get() = _user

    fun setUser(newUser: StateUser) {
        _user = newUser
    }

    fun goReg(controller: NavController) {
        controller.navigate(RoutesNavigation.REG)
    }

    fun signIn(controller: NavController) {
        if(user.login != "" && user.password != "") {
            viewModelScope.launch {
                try {
                    Constants.supabase.auth.signInWith(Email) {
                        email = user.login
                        password = user.password
                    }
                    currentUser = supabase.auth.currentUserOrNull()?.id//Пользователь авторизировался
                    controller.navigate(RoutesNavigation.SPLASH) {
                        popUpTo(RoutesNavigation.AUTH) {
                            inclusive = true
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Error screen AuthView",e.message.toString())
                    Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
