package com.example.makeyourselfapp.view.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.view.components.CircleGradient
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.PartGradient
import com.example.makeyourselfapp.view.components.TextTittlePrimary
import com.example.makeyourselfapp.view.ui.theme.AppDesign
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun StatisticsView(controller: NavHostController, viewModel: StatisticsViewModel = hiltViewModel()) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.launch()
    }

    if(state.loading) {
        CircularProgressCenter()
    }
    else
    {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(32.dp))
            TextTittlePrimary("Колесо баланса", Modifier.fillMaxWidth().align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(32.dp))
            Column (modifier = Modifier.fillMaxWidth()
                .background(AppDesign.colors.lightBackground)
                .padding(24.dp)){
                Box (modifier = Modifier.size(200.dp)){
                    CircleGradient()
                    val count = state.listSpheres.count()
                    val arc = (360 / count).toFloat()
                    var start = 0f
                    var r = 100

                    state.listSpheres.forEach { sphere ->
                        var countAllGoals = 0f
                        var countCompletedGoals = 0f
                        var percent = 0f
                        state.listGoals.forEach { goal ->
                            if (goal.idSphere == sphere.id) {
                                countAllGoals++
                                if (goal.status)
                                    countCompletedGoals++
                            }
                        }
                        if (countAllGoals != 0f)
                            percent = countCompletedGoals / countAllGoals
                        PartGradient(start, arc, percent)

                        val angleDegrees = (start + arc / 2)
                        val angleRadians = Math.toRadians(angleDegrees.toDouble())
                        val textX = (r * cos(angleRadians)) * 3
                        val textY = (r * sin(angleRadians)) * 3

                        Column{
                            Text(
                                text = sphere.name,
                                color = AppDesign.colors.textColor,
                                modifier = Modifier.align(Alignment.CenterHorizontally).offset { IntOffset(textX.toInt() + 280, textY.toInt() + 280)}
                            )
                        }

//                        val density = LocalDensity.current
//                        Text(
//                            text = sphere.name,
//                            color = AppDesign.colors.textColor,
//                            modifier = Modifier.offset {IntOffset(with(density) { textX }.toInt(), with(density) { textY }.toInt())},
//                            textAlign = TextAlign.Center
//                        )

                        start += arc
                    }
                }
            }
        }
    }
}