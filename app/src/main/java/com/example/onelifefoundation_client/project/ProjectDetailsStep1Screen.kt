package com.example.onelifefoundation_client.project

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.onelifefoundation_client.components.ImageWithText
import com.example.onelifefoundation_client.components.TopAppBard
import com.example.onelifefoundation_client.navigation.Screens

@Composable
fun ProjectDetailsStep1Screen(navController: NavController){
    val text = "By fall, Stallman was back within the mainstream\n" +
            "The anger eventually drove her son to focus on\n" +
            "At first, Stallman viewed these notices with\n" +
            "As hacks go, the GPL stands as one of Stallman's"
    Column {
        TopAppBard(title = "Project Join", navController = navController)
        ImageWithText(image="https://t3.ftcdn.net/jpg/01/66/79/00/360_F_166790001_DZILhvbWpKl4Z1OhHfirs2ARCjTLHoDL.jpg",
            text, buttonText = "Join", onClick = {
                navController.navigate(Screens.projectFieldStep2Screen)
            })
    }
}