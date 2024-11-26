package com.example.makeyourselfapp.view.screens.goals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.Constants.supabase
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.domain.repository.PrefManager.currentUser
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.screens.StateGoals
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

//Класс главной страницы приложения, содержащий список целей
@HiltViewModel
class GoalsViewModel @Inject constructor() : ViewModel() {
    private val _state by mutableStateOf(StateGoals())
    val state: StateGoals get() = _state

    fun launch(){
        viewModelScope.launch {
            if(currentUser != null) {
                val list = supabase.from("Goals").select() {
                    filter { eq("id_user", currentUser!!) }
                }.decodeList<Goals>()
                if (list.isNotEmpty()){
                    _state.notCompletedGoals = list.filter {it.status == false}
                    _state.completedGoals = list.filter { it.status == true}
                }
            }
            _state.loading.value = false
        }
    }

    //просмотр элемента листа целей с параметром item
    fun openItem(controller: NavHostController, item: Goals){
        val json = Json.encodeToString(Goals.serializer(), item)
        controller.navigate("${RoutesNavigation.INFO_GOAL}/$json")
    }

    //переход на страницу создания цели
    fun goCreateItem(controller: NavHostController){
        controller.navigate(RoutesNavigation.ITEM_GOAL) {
            popUpTo(RoutesNavigation.GOALS) {
                inclusive = true
            }
        }
    }
}