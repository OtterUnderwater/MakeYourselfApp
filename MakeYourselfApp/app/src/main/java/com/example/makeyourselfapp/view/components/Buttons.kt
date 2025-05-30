package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

/* Методы создания Button*/
@Composable
fun ButtonPrimary(text: String, enabled: Boolean = true, onClick: () -> Unit){
    Button (
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppDesign.colors.primary,
            disabledContainerColor = AppDesign.colors.secondary
        ),
        shape = RoundedCornerShape(15.dp),
        enabled = enabled
    ) {
        TextBodyBold(text, Modifier.padding(vertical = 8.dp))
    }
}

@Composable
fun ButtonColorIcon(icon: Int, color: Color, onClick: () -> Unit){
    Button (
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(15.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = ""
        )
    }
}