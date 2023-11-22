package com.example.onelifefoundation_client.project

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onelifefoundation_client.components.CheckBoxWithLabel
import com.example.onelifefoundation_client.components.FilledButtond
import com.example.onelifefoundation_client.components.OutlinedTextFieldD
import com.example.onelifefoundation_client.components.TopAppBard
import com.example.onelifefoundation_client.components.updateLabels
import com.example.onelifefoundation_client.navigation.Screens
import com.example.onelifefoundation_client.repository.ProjectDataSourceRepository
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectFieldStep2Screen(
    navController: NavHostController,
    projectName: String,
    projectLeader: String,
) {
    val projectDataSourceRepository = ProjectDataSourceRepository()
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contribution by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBard("Join Project", navController = navController)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {

        Column(modifier = Modifier.padding(20.dp))
        {

            Spacer(modifier = Modifier.size(55.dp))
            OutlinedTextFieldD(
                value = projectName,
                onValueChange = {
                    // If you want to allow changing the project name, you can handle it here
                },
                label = "Project name"
            )
            Spacer(modifier = Modifier.size(24.dp))
            OutlinedTextFieldD(
                value = projectLeader,
                onValueChange = {

                },
                label = "Project Leader"
            )

            Spacer(modifier = Modifier.size(24.dp))
            OutlinedTextFieldD(
                value = email,
                onValueChange = {
                    email = it
                },
                label = "Email"
            )

            Spacer(modifier = Modifier.size(24.dp))
            OutlinedTextFieldD(
                value = userName,
                onValueChange = {
                    userName = it
                },
                label = "Name"
            )

            Spacer(modifier = Modifier.size(24.dp))

            CheckBoxWithLabel(label = "Money") { isChecked: Boolean ->
                contribution = updateLabels("Money", isChecked, contribution)
            }

            CheckBoxWithLabel(label = "Skills") { isChecked: Boolean ->
                contribution = updateLabels("Skills", isChecked, contribution)
            }

            CheckBoxWithLabel(label = "Goods") { isChecked: Boolean ->
                contribution = updateLabels("Goods", isChecked, contribution)
            }


            Spacer(modifier = Modifier.size(24.dp))
            FilledButtond(
                onClick = {
                    if (email.isNotBlank() && userName.isNotBlank()) {
                        projectDataSourceRepository.addProjectJoinRequest(projectName,projectLeader,email,userName,contribution)
                        scope.launch {
                            snackbarHostState.showSnackbar("Successfully submitted")
                        }
                        // You can add the request to the database here
                        navController.navigate(Screens.finishScreen)
                    } else {
                        // Handle validation or error message
                        navController.navigate(Screens.finishScreen)
                    }
                },
                label = "Proceed"
            )
        }
    }
}