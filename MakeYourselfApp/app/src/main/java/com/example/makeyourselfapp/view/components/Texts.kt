package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun TextTittle(text: String){
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = AppDesign.colors.textColor
    )
}

@Composable
fun TextClickable(text: String, onClick: () -> Unit){
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = AppDesign.colors.textColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .offset(0.dp, 4.dp)
            .clickable(onClick = { onClick() }),
    )
}

