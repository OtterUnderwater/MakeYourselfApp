package com.example.makeyourselfapp.models.screens

import com.example.makeyourselfapp.view.screens.infoGoal.InfoGoalViewModel
import com.example.makeyourselfapp.view.screens.itemGoal.ItemGoalViewModel
import com.example.makeyourselfapp.view.screens.menu.MenuViewModel

data class VMForTask(
    var menuVM: MenuViewModel? = null,
    var itemGoalVM: ItemGoalViewModel? = null,
    var infoGoalVM: InfoGoalViewModel? = null,
)