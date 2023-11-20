package com.example.onelifefoundation_client.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onelifefoundation_client.R
import com.example.onelifefoundation_client.components.FilledButtond
import com.example.onelifefoundation_client.components.TopAppBard
import com.example.onelifefoundation_client.navigation.Screens

@Composable
fun FinishScreen(navController: NavController){
    val text="Thank you for your contribution!!!\n" +
            "You will receive a confirmation " +
            "email and someone will contact you " +
            "shortly regarding the project :)"
    Column {
        TopAppBard("Finish",navController=navController)
        Spacer(modifier= Modifier.size(16.dp))
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier=Modifier.padding(8.dp)){
            Text(text = text, fontSize = 18.sp)
            Spacer(modifier= Modifier.size(8.dp))
            Image(
                painter = painterResource(id = R.drawable.img_3),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            FilledButtond(
                onClick = { navController.navigate(Screens.homeScreen) },
                label ="Home",
                modifier = Modifier.align(Alignment.CenterHorizontally) as Modifier.Companion
            )
        }
    }
}