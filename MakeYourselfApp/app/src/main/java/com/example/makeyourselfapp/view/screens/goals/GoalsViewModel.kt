package com.example.makeyourselfapp.view.screens.goals

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
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(StateGoals())
    val state: StateGoals get() = _state.value

    fun launch(){
        viewModelScope.launch {
            if(currentUser != null) {
                val list = supabase.from("Goals").select() {
                    filter { eq("id_user", currentUser!!) }
                }.decodeList<Goals>()
                if (list != null){
                    _state.value.notCompletedGoals = list.filter { it -> it.status == false}
                    _state.value.completedGoals = list.filter { it -> it.status == true}
                }
            }
        }
    }

   fun openItem(controller: NavHostController, idItem: String){
       //controller.navigate(RoutesNavigation.ITEM_GOAL)
   }

   fun createItem(controller: NavHostController){
        controller.navigate(RoutesNavigation.ITEM_GOAL)
   }

}