package com.example.makeyourselfapp.view.screens.reg

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.makeyourselfapp.domain.Constants.supabase
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.models.database.Users
import com.example.makeyourselfapp.models.screens.StateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
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
        if(user.login != "" && user.password != "" && user.name != ""
            && user.twoPassword != "") {
            viewModelScope.launch {
                try {
                    val response = supabase.auth.signUpWith(Email) {
                        email = user.login
                        password = user.password
                    }
                    val currentUser = supabase.auth.currentUserOrNull()
                    if (user.password == user.twoPassword)
                    {
                        if (currentUser != null) {
                            val newUser = Users(id = currentUser.id, name = user.name, login = user.login)
                            supabase.from("Users").insert(newUser)
                            controller.navigate(RoutesNavigation.AUTH) {
                                popUpTo(RoutesNavigation.REG) {
                                    inclusive = true
                                }
                            }
                        }
                        else{
                            Toast.makeText(context, "Непредведенная ошибка", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: Exception) {
                    Log.e("Error screen SignUp",e.message.toString())
                    Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}