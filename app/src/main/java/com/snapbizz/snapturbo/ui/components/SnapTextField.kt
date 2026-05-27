package com.snapbizz.snapturbo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SnapTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,

    label: String? = null,
    placeholder: String? = null,

    type: SnapTextFieldType = SnapTextFieldType.TEXT,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,

    isError: Boolean = false,
    errorMessage: String? = null,

    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,

    imeAction: ImeAction = ImeAction.Done,
    onImeAction: (() -> Unit)? = null
) {

    val keyboardOptions = KeyboardOptions(
        keyboardType = when (type) {
            SnapTextFieldType.TEXT -> KeyboardType.Text
            SnapTextFieldType.PASSWORD -> KeyboardType.Password
            SnapTextFieldType.EMAIL -> KeyboardType.Email
            SnapTextFieldType.PHONE -> KeyboardType.Phone
            SnapTextFieldType.NUMBER -> KeyboardType.Number
        },
        imeAction = imeAction
    )

    val visualTransformation =
        if (type == SnapTextFieldType.PASSWORD)
            PasswordVisualTransformation()
        else
            VisualTransformation.None

    Column(modifier = modifier) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            singleLine = singleLine,
            isError = isError,

            label = label?.let { { Text(it) } },
            placeholder = placeholder?.let { { Text(it) } },

            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,

            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onAny = { onImeAction?.invoke() }
            ),

            visualTransformation = visualTransformation,
            modifier = Modifier.fillMaxWidth()
        )

        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

enum class SnapTextFieldType {
    TEXT,
    PASSWORD,
    EMAIL,
    PHONE,
    NUMBER
}


/*
* ✅ Normal Text
SnapTextField(
    value = name,
    onValueChange = { name = it },
    label = "Name"
)

🔐 Password Field
SnapTextField(
    value = password,
    onValueChange = { password = it },
    label = "Password",
    type = SnapTextFieldType.PASSWORD
)

📧 Email
SnapTextField(
    value = email,
    onValueChange = { email = it },
    label = "Email",
    type = SnapTextFieldType.EMAIL
)

📱 Phone (Digits Only)
SnapTextField(
    value = phone,
    onValueChange = {
        if (it.all { ch -> ch.isDigit() }) phone = it
    },
    label = "Phone",
    type = SnapTextFieldType.PHONE
)

❌ Error State
SnapTextField(
    value = email,
    onValueChange = { email = it },
    label = "Email",
    isError = true,
    errorMessage = "Invalid email address"
)*/
