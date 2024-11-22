package com.example.makeyourselfapp.view.screens.itemGoal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.example.makeyourselfapp.models.screens.StateItemGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemGoalViewModel @Inject constructor() : ViewModel() {

    private var _state by mutableStateOf(StateItemGoal())
    val state: StateItemGoal get() = _state

    fun setGoals(newGoals: Goals) {
        _state = _state.copy(goal = newGoals)
    }

    fun addTasks(newTasks: Tasks) {
        _state.listTasks.add(newTasks)
    }

    fun launch(){
        viewModelScope.launch {
            _state.listSphere = supabase.from("Spheres").select()
            { filter { eq("id_user", currentUser!!) } }.decodeList<Spheres>()
            _state.listCategories = supabase.from("Categories").select().decodeList<Categories>()
            _state.loading = false
        }
    }

    fun changeStatus(name: String, value: Boolean){
        val newList = state.listTasks.map { task ->
            if (task.nameTask == name) task.copy(status = value)
            else task
        }.toMutableList()
        _state = _state.copy(listTasks = newList)
    }

    fun createGoal(controller: NavHostController){
        viewModelScope.launch {
            val goal = supabase.from("Goals").insert(_state.goal) {
                select()
            }.decodeSingle<Goals>()
            _state.listTasks.forEach {
                supabase.from("Tasks").insert(it.copy(idGoal = goal.id))
            }
        }
        controller.navigate(RoutesNavigation.GOALS) {
            popUpTo(RoutesNavigation.ITEM_GOAL) {
                inclusive = true
            }
        }
    }
}