package com.example.makeyourselfapp.view.screens.infoGoal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.domain.Constants.supabase
import com.example.makeyourselfapp.domain.navigation.RoutesNavigation
import com.example.makeyourselfapp.domain.repository.PrefManager.currentUser
import com.example.makeyourselfapp.models.database.Categories
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres
import com.example.makeyourselfapp.models.database.Tasks
import com.example.makeyourselfapp.models.screens.StateInfoGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoGoalViewModel @Inject constructor() : ViewModel() {
    private var _state = MutableStateFlow(StateInfoGoal())
    val state: StateFlow<StateInfoGoal> = _state.asStateFlow()

    fun setState(value: StateInfoGoal){
        _state.value = value
    }

    var stateValue: StateInfoGoal
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun launch(){
        viewModelScope.launch {
            stateValue.listSpheres = supabase.from("Spheres").select()
            { filter { eq("id_user", currentUser!!) } }.decodeList<Spheres>()
            stateValue.listTasks = supabase.from("Tasks").select()
            { filter { eq("id_goal", state.value.goal.id) } }.decodeList<Tasks>().toMutableList()
            stateValue.listCategories = supabase.from("Categories").select().decodeList<Categories>()
            stateValue.loading = false
        }
    }

    fun changeStatusGoal(value: Boolean){
        viewModelScope.launch {
            supabase.from("Goals").update( {
                set("status", value)
            }
            ) {
                filter {
                    eq("id", stateValue.goal.id)
                }
            }
        }
        stateValue = stateValue.copy(goal = stateValue.goal.copy(status = value))
    }

    fun changeStatusTask(id: String, value: Boolean) {
        viewModelScope.launch {
            supabase.from("Tasks").update( {
                set("status", value)
            }
            ) {
                filter {
                    eq("id", id)
                }
            }
        }
        val newList = stateValue.listTasks.map { task ->
            if (task.id == id) task.copy(status = value)
            else task
        }.toMutableList()
        stateValue = stateValue.copy(listTasks = newList)
    }

/*    fun changeGoal(newTask: Tasks) {
        viewModelScope.launch {
            supabase.from("Goals").update(
                {
                    set("name", _state.value.goal.name)
                    set("description", _state.value.goal.description)
                    set("id_sphere", _state.value.goal.idSphere)
                    set("status", _state.value.goal.status)
                }
            ) {
                filter {
                    eq("id", _state.value.goal.id)
                }
            }
        }

    }*/

    fun deleteGoal(controller: NavHostController) {
        viewModelScope.launch {
            supabase.from("Goals").delete {
                filter {
                    eq("id", _state.value.goal.id)
                }
            }
        }
        controller.navigate(RoutesNavigation.GOALS) {
            popUpTo(RoutesNavigation.ITEM_GOAL) {
                inclusive = true
            }
        }
    }

}