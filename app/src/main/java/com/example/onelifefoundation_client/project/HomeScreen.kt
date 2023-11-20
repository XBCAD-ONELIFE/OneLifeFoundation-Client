package com.example.onelifefoundation_client.project

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.onelifefoundation_client.navigation.Screens
import com.example.onelifefoundation_client.repository.Project
import com.example.onelifefoundation_client.repository.ProjectDataSourceRepository
import com.example.onelifefoundation_client.components.ProjectViewList
import com.example.onelifefoundation_client.components.SearchBarD
import com.example.onelifefoundation_client.components.Title
import com.example.onelifefoundation_client.components.TopAppBarWithNoIcon



@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val searchValue = remember { mutableStateOf("") }

    val projectDataSourceRepository = ProjectDataSourceRepository()
    // Get the list of projects from the data source repository
    val projects = projectDataSourceRepository.getAllProjects()
        .collectAsState(emptyList()).value

    val onDonateClick: (Project) -> Unit = { project ->
        val donateUrl = "https://www.paypal.com/your_paypal"
        navController.navigate("${Screens.webViewScreen}?url=$donateUrl") {
            launchSingleTop = true
        }
    }

    val onJoinClick: (Project) -> Unit = { project ->
        try {
            val projectName = project.projectName ?: throw IllegalArgumentException("projectName is null")
            val projectLeader = project.projectLeader ?: throw IllegalArgumentException("projectLeader is null")

            val route = "${Screens.projectFieldStep2Screen}/$projectName/$projectLeader"
            navController.navigate(route)
        } catch (e: Exception) {
            Log.e("JoinButtonClick", "Error navigating to projectFieldStep2Screen", e)
        }
    }



    // Filter projects based on the search value
    val filteredProjects = if (searchValue.value.isEmpty()) {
        projects
    } else {
        projects.filter { project ->
            project.projectName.contains(searchValue.value, ignoreCase = true)
        }
    }

    Column {
        TopAppBar(
            navigationIcon = {},
            actions = {
                IconButton(onClick = { projectDataSourceRepository?.signOut()
                    navController.navigate(Screens.signInScreen)
                }) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = null
                    )
                }
            },
            title ={

                Text(text = "Home")
            }
        )
        Column(modifier = Modifier.padding(8.dp, 4.dp, 8.dp, 0.dp)) {
            SearchBarD(
                text = searchValue,
                label = "Search for Project",
                placeholder = "Search for Project",
                trailing = { Icon(Icons.Default.Search, contentDescription = null) },
            )

            Spacer(modifier = Modifier.size(2.dp))
            Title(title = "Projects")
            Spacer(modifier = Modifier.size(2.dp)
            )

            Column {
                ProjectViewList(filteredProjects, onDonateClick, onJoinClick)
            }
        }
    }
}
