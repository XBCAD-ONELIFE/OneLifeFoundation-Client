package com.example.onelifefoundation_client.repository



import kotlinx.coroutines.flow.Flow

class ProjectDataSourceRepository {

    private val dataSource = DataSource()
    // Add a function to edit a project

    fun getAllProjects(): Flow<List<Project>> {
        return dataSource.getAllProjects()
    }

    fun signOut() = dataSource.signOut()

    fun addProjectJoinRequest(
        projectName: String,
        projectLeader: String,
        userEmail: String,
        userName: String,
        contribution: String
    ) {
        dataSource.addProjectJoinRequest(projectName,projectLeader,userEmail, userName, contribution)
    }
}
