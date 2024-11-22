package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun TextTittlePrimary(text: String, modifier: Modifier?){
    Text(
        text = text,
        style = AppDesign.typography.titleLarge,
        color = AppDesign.colors.primary,
        modifier = modifier?: Modifier
    )
}

@Composable
fun TextTittle(text: String, modifier: Modifier?){
    Text(
        text = text,
        style = AppDesign.typography.titleMedium,
        color = AppDesign.colors.textColor,
        modifier = modifier?: Modifier
    )
}

@Composable
fun TextBodyBold(text: String, modifier: Modifier?){
    Text(
        text = text,
        style = AppDesign.typography.bodyBold,
        color = AppDesign.colors.textColor,
        modifier = modifier?: Modifier
    )
}

@Composable
fun TextBodyMedium(text: String, modifier: Modifier?){
    Text(
        text = text,
        style = AppDesign.typography.bodyMedium,
        color = AppDesign.colors.textColor,
        modifier = modifier?: Modifier
    )
}

@Composable
fun TextClickable(text: String, onClick: () -> Unit){
    Text(
        text = text,
        style = AppDesign.typography.titleMedium,
        color = AppDesign.colors.primary,
        modifier = Modifier.clickable(onClick = { onClick() })
    )
}

@Composable
fun AnswerClickable(text: String, onClick: () -> Unit){
    Column (modifier = Modifier.fillMaxWidth().padding(8.dp)){
        Text(
            text = text,
            style = AppDesign.typography.titleLarge,
            color = AppDesign.colors.accent,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .clickable(onClick = { onClick() })
        )
    }
}