package com.example.onelifefoundation_client.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.example.onelifefoundation_client.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.img,
        title = "OneLife Foundation",
        description = "Welcome to the OneLife Foundation app."
    )

    object Second : OnBoardingPage(
        image = R.drawable.img_1,
        title = "Make a difference",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third : OnBoardingPage(
        image = R.drawable.img,
        title = "Let's partner together",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Four : OnBoardingPage(
        image = R.drawable.img_1,
        title = "You're ready",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}