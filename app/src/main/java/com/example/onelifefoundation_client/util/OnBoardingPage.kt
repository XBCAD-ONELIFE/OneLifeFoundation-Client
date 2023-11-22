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
        image = R.drawable.dancer_2302208,
        title = "Welcome!!! ",
        description = "We're so glad you've made your way here :D"
    )

    object Second : OnBoardingPage(
        image = R.drawable.img_1,
        title = "Make a difference",
        description = "This is your opportunity to partner with us and do your part in your community"
    )

    object Third : OnBoardingPage(
        image = R.drawable.helpinghands,
        title = "Donate, Sign Up or get more info",
        description = "You can either donate money to projects, arrange to give some goods, or give of your time"
    )

    object Four : OnBoardingPage(
        image = R.drawable.img_3,
        title = "You're ready",
        description = "Sign Up to see the various projects going on, see you soon ;)"
    )
}