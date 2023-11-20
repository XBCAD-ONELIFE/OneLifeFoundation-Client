package com.example.onelifefoundation_client.repository



data class JoinRequest(
    val projectName: String = "",
    val projectLeader: String = " ",
    val userEmail: String = "",
    val userName: String = " "
)
