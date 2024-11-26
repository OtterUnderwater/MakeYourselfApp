package com.example.makeyourselfapp.view.screens.infoGoal

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
import com.example.makeyourselfapp.models.screens.StateInfoGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject

//Данный класс выполняет различные запросы с БД, редактирование и переход по страницам
@HiltViewModel
class InfoGoalViewModel @Inject constructor() : ViewModel() {
    private var _state by mutableStateOf(StateInfoGoal())
    val state: StateInfoGoal get() = _state

    fun launch(){
        viewModelScope.launch {
            _state.listSpheres = supabase.from("Spheres").select()
            { filter { eq("id_user", currentUser!!) } }.decodeList<Spheres>()
            _state.listTasks = supabase.from("Tasks").select()
            { filter { eq("id_goal", state.goal.id) } }.decodeList<Tasks>().toMutableList()
            _state.listCategories = supabase.from("Categories").select().decodeList<Categories>()
            _state.loading.value = false
        }
    }

    //изменение состояния
    fun setGoals(newGoals: Goals) {
        _state = _state.copy(goal = newGoals)
    }

    //добавление задачи в лист
    fun addTasks(newTasks: Tasks) {
        viewModelScope.launch {
            supabase.from("Tasks").insert(newTasks.copy(idGoal = state.goal.id))
        }
        _state.listTasks.add(newTasks)
    }

    //возвращение на основную страницу списка всех целей
    fun goToGoals(controller: NavHostController) {
        controller.navigate(RoutesNavigation.GOALS) {
            popUpTo(RoutesNavigation.INFO_GOAL) {
                inclusive = true
            }
        }
    }

    //изменение состояния цели
    fun changeStatusGoal(goal: Goals){
        viewModelScope.launch {
            supabase.from("Goals").update( {
                set("status", goal.status)
            }
            ) {
                filter {
                    eq("id", goal.id)
                }
            }
        }
        _state = _state.copy(goal = goal)
    }

    //изменение статуса задачи
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
        val newList = state.listTasks.map { task ->
            if (task.id == id) task.copy(status = value)
            else task
        }.toMutableList()
        _state = state.copy(listTasks = newList)
    }

    //изменение информации о цели
    fun changeGoal(newGoal: Goals) {
        viewModelScope.launch {
            supabase.from("Goals").update(
                {
                    set("name", newGoal.name)
                    set("description", newGoal.description)
                    set("id_sphere", newGoal.idSphere)
                    set("status", newGoal.status)
                }
            ) {
                filter {
                    eq("id", newGoal.id)
                }
            }
        }
        _state = _state.copy(goal = newGoal)
    }

    //удаление цели
    fun deleteGoal(controller: NavHostController) {
        viewModelScope.launch {
            supabase.from("Goals").delete {
                filter {
                    eq("id", _state.goal.id)
                }
            }
        }
        controller.navigate(RoutesNavigation.GOALS) {
            popUpTo(RoutesNavigation.ITEM_GOAL) {
                inclusive = true
            }
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
}