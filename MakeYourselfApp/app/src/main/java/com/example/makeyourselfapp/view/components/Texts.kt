package com.example.makeyourselfapp.view.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
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