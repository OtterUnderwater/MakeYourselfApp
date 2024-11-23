package com.example.makeyourselfapp.view.screens.statistics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeyourselfapp.domain.Constants.supabase
import com.example.makeyourselfapp.domain.repository.PrefManager.currentUser
import com.example.makeyourselfapp.models.database.Goals
import com.example.makeyourselfapp.models.database.Spheres
import com.example.makeyourselfapp.models.screens.StateStatistics
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor() : ViewModel() {
    private var _state by mutableStateOf(StateStatistics())
    val state: StateStatistics get() = _state

    fun launch(){
        viewModelScope.launch {

            _state.listSpheres = supabase.from("Spheres").select() {
                filter { eq("id_user", currentUser!!) }
            }.decodeList<Spheres>()
            _state.listGoals = supabase.from("Goals").select() {
                filter { eq("id_user", currentUser!!) }
            }.decodeList<Goals>()
            _state.loading = false
        }
    }


}