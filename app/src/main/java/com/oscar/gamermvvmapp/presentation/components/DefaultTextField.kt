package com.oscar.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
    value: String,
    onChangeValue: (value: String) -> Unit,
    validateField: () -> Unit = {},
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    icon: ImageVector = Icons.Default.Lock,
    containerColor: Color = MaterialTheme.colorScheme.background,
    unfocusedIndicatorColor: Color = Color.Transparent,
    hideText: Boolean = false,
    errorMsg: String = ""
) {
    Column {
        TextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onChangeValue(it)
                validateField()
            },
            placeholder = { Text(text = placeHolder) },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            leadingIcon = { Icon(imageVector = icon, contentDescription = null, tint = Color.White) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = containerColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor
            ),
            shape = Shapes().small,
            visualTransformation = if(hideText) PasswordVisualTransformation() else VisualTransformation.None
        )
        Text(
            modifier = Modifier.padding(top = 5.dp, start = 10.dp),
            text= errorMsg,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error
        )
    }
}