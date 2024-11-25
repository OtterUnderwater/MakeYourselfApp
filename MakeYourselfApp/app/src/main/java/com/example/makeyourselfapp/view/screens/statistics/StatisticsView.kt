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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.models.screens.Сoordinates
import com.example.makeyourselfapp.view.components.CircleGradient
import com.example.makeyourselfapp.view.components.CircularProgressCenter
import com.example.makeyourselfapp.view.components.PartGradient
import com.example.makeyourselfapp.view.components.TextBodyLight
import com.example.makeyourselfapp.view.components.TextTittle
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

    if(state.loading.value) {
        CircularProgressCenter()
    }
    else
    {
        Column (Modifier.verticalScroll(rememberScrollState())){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(24.dp))

                TextTittlePrimary("Колесо баланса",
                    Modifier.fillMaxWidth().align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(24.dp))

                Column (modifier = Modifier
                    .fillMaxWidth()
                    .background(AppDesign.colors.lightBackground, RoundedCornerShape(16.dp))
                    .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Box (modifier = Modifier.size(200.dp)){
                        CircleGradient()
                        val count = state.listSpheres.count()
                        val arc = (360 / count).toFloat()
                        var start = 0f
                        var r = 100
                        var listXY: MutableList<Сoordinates> = mutableListOf()

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
                            val textX = (r * cos(angleRadians)) * 3.2
                            val textY = (r * sin(angleRadians)) * 3.2

                            listXY.add(Сoordinates(x = textX.toInt() + 285, y = textY.toInt() + 265))

                            start += arc
                        }
                        val density = LocalDensity.current
                        val xOffsetPx = with(density) { (60.dp).roundToPx() } - 15
                        val yOffsetPx = with(density) { (20.dp).roundToPx() } - 30
                        listXY.forEachIndexed(){ index, it ->
                          TextBodyLight(state.listSpheres[index].name,
                                Modifier.offset {
                                    IntOffset(it.x - xOffsetPx, it.y - yOffsetPx)
                                }
                            )
                        }


                    }
                }

                TextTittle("Количество достигнутых целей:",
                    Modifier.fillMaxWidth().padding(top = 32.dp, bottom = 24.dp)
                        .align(Alignment.CenterHorizontally), TextAlign.Center)

                Box (modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)){
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.count_goals),
                        contentDescription = "",
                        tint = AppDesign.colors.primary,
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center)
                    )
                    Text(
                        text = state.listGoals.count { it.status == true }.toString(),
                        style = AppDesign.typography.titleLarge,
                        color = AppDesign.colors.textColor,
                        fontSize = 48.sp,
                        modifier =  Modifier.fillMaxWidth().align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }


            }
        }
    }
}