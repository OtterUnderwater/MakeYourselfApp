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

//Данный класс выполняет различные запросы с БД, редактирование и переход по страницам
@HiltViewModel
class ItemGoalViewModel @Inject constructor() : ViewModel() {
    private var _state by mutableStateOf(StateItemGoal())
    val state: StateItemGoal get() = _state

    fun launch(){
        viewModelScope.launch {
            _state.listSpheres = supabase.from("Spheres").select()
            { filter { eq("id_user", currentUser!!) } }.decodeList<Spheres>()
            _state.listCategories = supabase.from("Categories").select().decodeList<Categories>()
            _state.loading.value = false
        }
    }

    //добавление задачи
    fun addTasks(newTasks: Tasks) {
        _state.listTasks.add(newTasks)
    }

    //изменение статуса задачи
    fun changeStatus(id: String, value: Boolean){
        val newList = state.listTasks.map { task ->
            if (task.id == id) task.copy(status = value)
            else task
        }.toMutableList()
        _state = _state.copy(listTasks = newList)
    }

    //создание цели и сохранение в бд, учитывая связанные с целью задачи
    fun createGoal(controller: NavHostController, newGoal: Goals){
        _state = state.copy(goal = newGoal)
        viewModelScope.launch {
            val goal = supabase.from("Goals").insert(newGoal) {
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

    //удаление задачи
    fun deleteTask(deletedTask: Tasks) {
        viewModelScope.launch {
            _state.loading.value = true
            supabase.from("Tasks").delete {
                filter {
                    eq("id", deletedTask.id)
                }
            }
            _state.listTasks.remove(deletedTask)
            _state.loading.value = false
        }
    }

    //изменение задачи
    fun changeTask(newTask: Tasks) {
        viewModelScope.launch {
            supabase.from("Tasks").update(
                {
                    set("name_task", newTask.nameTask)
                    set("description", newTask.description)
                    set("id_category", newTask.idCategory)
                }
            ) {
                filter {
                    eq("id", newTask.id)
                }
            }
            val newList = state.listTasks.map { task ->
                if (task.id == newTask.id) task.copy(nameTask = newTask.nameTask, description = newTask.description, idCategory = newTask.idCategory)
                else task
            }.toMutableList()
            _state = _state.copy(listTasks = newList)
        }
    }
}