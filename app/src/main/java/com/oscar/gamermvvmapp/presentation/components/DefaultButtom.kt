package com.oscar.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oscar.gamermvvmapp.presentation.utils.Vibrate


@Composable
fun DefaultButtom(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
    onClick: () -> Unit,
    colorButtom: Color = MaterialTheme.colorScheme.primary,
    colorText: Color = MaterialTheme.colorScheme.onBackground,
    icon: ImageVector = Icons.Default.ArrowForward,
    enabled: Boolean = true
) {
    val context = LocalContext.current

    Column {
        Button(
            modifier = modifier,
            onClick = {
                onClick()
                Vibrate.vibrar(context)
                      },
            colors = ButtonDefaults.buttonColors(containerColor = colorButtom),
            shape = Shapes().small,
            enabled = enabled
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text, color = colorText)
        }
    }
}