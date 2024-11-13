package com.example.makeyourselfapp.view.screens.auth

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.Constants
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.domain.repository.PrefManager.status
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

    private val _user = mutableStateOf(StateUser())
    val user: StateUser get() = _user.value

    fun setUser(newUser: StateUser) {
        _user.value = newUser
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
                    status = 1 //Пользователь авторизировался
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
