package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBase(value: String, input: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value, //текущее значение
        onValueChange = {input(it) }, //передаем введенный текст в лямда функцию
        textStyle = AppDesign.typography.bodyMedium.copy(color = AppDesign.colors.textColor),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp, shape = RoundedCornerShape(30), spotColor = Color(
                    Black.value
                )
            ),
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = AppDesign.colors.primary,
            containerColor = AppDesign.colors.lightBackground,
            cursorColor = AppDesign.colors.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPassword(value: String, input: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value, //текущее значение
        onValueChange = { input(it) }, //передаем введенный текст
        textStyle = AppDesign.typography.bodyMedium.copy(color = AppDesign.colors.textColor),
        modifier = Modifier
            .fillMaxWidth()
            .shadow( elevation = 4.dp, shape = RoundedCornerShape(30)),
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = AppDesign.colors.primary,
            containerColor =  AppDesign.colors.lightBackground,
            cursorColor = AppDesign.colors.primary
        )
    )
}