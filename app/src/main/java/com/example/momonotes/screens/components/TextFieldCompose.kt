package com.example.momonotes.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TextFieldCompleto(
    isError: Boolean,
    value: String,
    onChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        isError = isError,
        leadingIcon = if (icon != null) {
            { Icon(imageVector = icon, contentDescription = null) }
        } else {
            null
        }
    )
}