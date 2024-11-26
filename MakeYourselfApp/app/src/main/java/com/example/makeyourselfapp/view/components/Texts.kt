package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

/* Методы для текста*/
@Composable
fun TextTittlePrimary(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        style = AppDesign.typography.titleLarge,
        color = AppDesign.colors.primary,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextTittle(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start){
    Text(
        text = text,
        style = AppDesign.typography.titleMedium,
        color = AppDesign.colors.textColor,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun TextBodyBold(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        style = AppDesign.typography.bodyBold,
        color = AppDesign.colors.textColor,
        modifier = modifier
    )
}

@Composable
fun TextBodyMedium(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        style = AppDesign.typography.bodyMedium,
        color = AppDesign.colors.textColor,
        modifier = modifier
    )
}

@Composable
fun TextClickable(text: String, modifier: Modifier = Modifier, onClick: () -> Unit){
    Text(
        text = text,
        style = AppDesign.typography.titleMedium,
        color = AppDesign.colors.primary,
        modifier = modifier.clickable(onClick = { onClick() })
    )
}

@Composable
fun AnswerClickable(text: String, onClick: () -> Unit){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)){
        Text(
            text = text,
            style = AppDesign.typography.titleLarge,
            color = AppDesign.colors.accent,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable(onClick = { onClick() })
        )
    }
}

@Composable
fun TextBodyLight(text: String, modifier: Modifier = Modifier){
    Column (modifier.size(120.dp, 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            style = AppDesign.typography.bodyLight,
            color = AppDesign.colors.textColor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
