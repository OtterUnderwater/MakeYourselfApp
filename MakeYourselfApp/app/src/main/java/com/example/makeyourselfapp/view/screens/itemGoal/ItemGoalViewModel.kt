package com.example.makeyourselfapp.view.screens.itemGoal

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeyourselfapp.domain.Constants.supabase
import com.example.makeyourselfapp.domain.repository.PrefManager.currentUser
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres
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
        _state.goals = newGoals
    }

    fun launch(){
        viewModelScope.launch {
            _state.listSphere = supabase.from("Spheres").select()
            { filter { eq("id_user", currentUser!!) } }.decodeList<Spheres>()
            _state.loading = false
        }
    }
}