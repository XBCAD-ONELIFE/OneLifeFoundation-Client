package com.example.onelifefoundation_client.repository


data class Project(

    val projectName: String = "",
    val projectLeader: String = "",
    val projectLocation: String = "",
    val projectDescription: String = "",
    val imageUrls: List<String> = emptyList(),
    val image: String = ""

)
