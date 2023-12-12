package com.oscar.gamermvvmapp.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.navigation.screen.auth.AuthScreen

@Composable
fun LoginBottomBar(navHostController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically    ) {
        Text(text = "¿No tienes cuenta?",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.clickable { navHostController.navigate(route = AuthScreen.Signup.route)  },
            text = "REGISTRATE AQUI",
            color =  MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}