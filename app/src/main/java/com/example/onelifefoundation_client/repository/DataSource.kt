package com.example.onelifefoundation_client.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class DataSource {
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference


    fun getAllProjects(): Flow<List<Project>> {
        return flow {
            val querySnapshot = db.collection("addProject").get().await()
            val projects = mutableListOf<Project>()

            for (document in querySnapshot.documents) {
                val project = document.toObject(Project::class.java)
                project?.let {
                    projects.add(it)
                }
            }

            emit(projects)
        }
    }
    fun signOut() = Firebase.auth.signOut()

    fun addProjectJoinRequest(
        projectName: String,
        projectLeader: String,
        userEmail: String,
        userName: String

    ) {
        val joinRequestMap = hashMapOf(
            "projectName" to projectName,
            "projectLeader" to projectLeader,
            "userEmail" to userEmail,
            "userName" to userName
        )

        db.collection("JoinRequests").document(userName).set(joinRequestMap)
            .addOnCompleteListener {
                // Handle success
            }
            .addOnFailureListener {
                // Handle failure
            }
    }


}
