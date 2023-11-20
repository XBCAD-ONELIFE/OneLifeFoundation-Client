package com.example.onelifefoundation_client.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldD(
    value: String,
    onValueChange: (String) -> Unit,
    label: String= "",
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue->
            onValueChange.invoke(newValue)
        },
        label = { Text(text = label) },
        placeholder = {Text(text=label)}
    )
}

@Composable
fun TextFieldBasic(
    text: MutableState<String>,
    label: String,

    ){
//    var passwordVisible = rememberSaveable { mutableStateOf(fieldType != FieldType.Password) }
//    val visibilityIcon =
//        if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility

    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text.value,
            onValueChange = {
                text.value = it
            },
            label = { Text(text = label) },
            placeholder = { Text(text = label) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                cursorColor = MaterialTheme.colors.primary,
                leadingIconColor = MaterialTheme.colors.primaryVariant,
                trailingIconColor = MaterialTheme.colors.primaryVariant,
                focusedLabelColor = MaterialTheme.colors.secondary,
                unfocusedLabelColor = MaterialTheme.colors.onSecondary,
                errorLabelColor = MaterialTheme.colors.error,
                errorCursorColor =  MaterialTheme.colors.onError,
                errorTrailingIconColor = MaterialTheme.colors.error,
            ),

            )
//        if (errorMessage.isNotEmpty()) {
//            Spacer(Modifier.height(6.dp))
//            Text(
//                text = errorMessage,
//                color = MaterialTheme.colors.error,
//                fontSize = 16.sp,
//                modifier = Modifier.padding(start = 6.dp)
//            )
//        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxD(
    text:List<String>,
    label:String
) {
    var suggestions =text
    var expanded = remember { mutableStateOf(false) }
    var selectedText = remember { mutableStateOf(suggestions[0]) }


    Column {
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = {
                expanded.value = it
            },
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            OutlinedTextField(
                value = selectedText.value,
                onValueChange = {
                    selectedText.value=it
                },
                label = {
                    Text(text = label)},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                modifier = Modifier.menuAnchor(),
            )

            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                suggestions.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedText.value = item
                            expanded.value = false
                        }
                    ){
                        Text(text = item)
                    }
                }
            }
        }
    }
}