package com.example.makeyourselfapp.view.screens.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeyourselfapp.domain.Constants.supabase
import com.example.makeyourselfapp.domain.repository.PrefManager.currentUser
import com.example.makeyourselfapp.models.database.Categories
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Tasks
import com.example.makeyourselfapp.models.screens.StateMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject

//Данный класс выполняет различные запросы с БД, редактирование и переход по страницам
@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel()  {
    private var _state by mutableStateOf(StateMenu())
    val state: StateMenu get() = _state

    fun launch(){
        viewModelScope.launch {
            _state.listCategories = supabase.from("Categories").select().decodeList<Categories>()
            val listTasks = supabase.from("Tasks").select().decodeList<Tasks>()
            val listGoals = supabase.from("Goals").select()
            { filter { eq("id_user", currentUser!!) } }.decodeList<Goals>()
            if (listGoals.isNotEmpty() && listTasks.isNotEmpty() )
            {
                listGoals.forEach { goal ->
                    listTasks.forEach { task ->
                        if (task.idGoal == goal.id){
                            _state.listTasks.add(task)
                        }
                    }
                }
            }
            _state.loading.value = false
        }
    }

    //смена статуса
    fun changeStatus(id: String, value: Boolean) {
        val newList = state.listTasks.map { task ->
            if (task.id == id) task.copy(status = value)
            else task
        }.toMutableList()
        _state = _state.copy(listTasks = newList)
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
    }

    //изменение задачи и обновление данных в бд
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

    //удаление задачи из бд
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