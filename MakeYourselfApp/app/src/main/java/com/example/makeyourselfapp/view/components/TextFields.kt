package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

/* Методы для TextField*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBase(value: String, placeholder: String, input: (String) -> Unit) {
    OutlinedTextField(
        value = value, //текущее значение
        onValueChange = { input(it) }, //передаем введенный текст в лямда функцию
        textStyle = AppDesign.typography.bodyMedium.copy(color = AppDesign.colors.textColor),
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder, color = AppDesign.colors.additional) },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
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
fun TextFieldSmall(value: String, placeholder: String, input: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { input(it) },
        textStyle = AppDesign.typography.bodyMedium.copy(color = AppDesign.colors.textColor),
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder, color = AppDesign.colors.additional) },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = outlinedTextFieldColors(
            unfocusedBorderColor = AppDesign.colors.primary,
            focusedBorderColor = AppDesign.colors.primary,
            containerColor = AppDesign.colors.lightBackground,
            cursorColor = AppDesign.colors.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBig(value: String, placeholder: String, input: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { input(it) },
        textStyle = AppDesign.typography.bodyMedium.copy(color = AppDesign.colors.textColor),
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder, color = AppDesign.colors.additional) },
        singleLine = false,
        shape = RoundedCornerShape(16.dp),
        colors = outlinedTextFieldColors(
            unfocusedBorderColor = AppDesign.colors.primary,
            focusedBorderColor = AppDesign.colors.primary,
            containerColor = AppDesign.colors.lightBackground,
            cursorColor = AppDesign.colors.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPassword(value: String, placeholder: String, input: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { input(it) },
        textStyle = AppDesign.typography.bodyMedium.copy(color = AppDesign.colors.textColor),
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder, color = AppDesign.colors.additional) },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = AppDesign.colors.primary,
            containerColor =  AppDesign.colors.lightBackground,
            cursorColor = AppDesign.colors.primary
        )
    )
}