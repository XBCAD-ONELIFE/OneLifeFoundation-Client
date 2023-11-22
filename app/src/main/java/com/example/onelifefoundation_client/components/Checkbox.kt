package com.example.onelifefoundation_client.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onelifefoundation_client.navigation.Screens

@Composable
fun CheckBoxWithLabel(label: String, onCheckedChange: (Boolean) -> Unit) {
    val checked = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
                onCheckedChange(it)
            }
        )
        ClickableText(text = AnnotatedString(label), onClick = {
            checked.value = !checked.value
            onCheckedChange(checked.value)
        })
    }
}



@Composable
fun CheckBoxTP(navController: NavController, front: String, back: String, focusRequester: FocusRequester): MutableState<Boolean> {
    var agreedToTerms = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Checkbox(
            checked = agreedToTerms.value,
            onCheckedChange = { newCheckedState ->
                agreedToTerms.value = newCheckedState
            },
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterVertically)
                .focusRequester(focusRequester) // Apply the focusRequester here
        )

        ClickableText(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(front)
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(back)
                }
            },
            onClick = { offset ->
                // Navigate to the terms and privacy screen
                navController.navigate(Screens.TermsAndPrivacyScreen)
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

    return agreedToTerms
}


// Function to update the string of labels
fun updateLabels(label: String, isChecked: Boolean, labels: String): String {
    val updatedLabels = if (isChecked) {
        if (labels.isEmpty()) label else "$labels, $label"
    } else {
        labels.replaceFirst("$label, ", "").replaceFirst(", $label", "").replace(label, "")
    }
    return updatedLabels.trim(',')
}


