package com.example.onelifefoundation_client.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.onelifefoundation_client.authentication.ForgotPasswordScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.onelifefoundation_client.authentication.LoginViewModel
import com.example.onelifefoundation_client.authentication.SignInScreen
import com.example.onelifefoundation_client.authentication.SignUpScreen

import com.example.onelifefoundation_client.onboarding.WelcomeScreen
import com.example.onelifefoundation_client.project.FinishScreen
import com.example.onelifefoundation_client.project.HomeScreen
import com.example.onelifefoundation_client.project.ProjectFieldStep2Screen
import com.example.onelifefoundation_client.project.ProjectDetailsStep1Screen
import com.example.onelifefoundation_client.project.WebViewScreen
import com.example.onelifefoundation_client.termsAndprivacy.TermsAndPrivacyScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun AppNavigation(navController: NavHostController, loginViewModel: LoginViewModel
){
    NavHost(navController = navController, startDestination = Screens.welcomeScreen ){
        composable(route=Screens.welcomeScreen){
            WelcomeScreen(navController)
        }
        composable(route=Screens.signUpScreen){
            SignUpScreen(navController, loginViewModel)
        }
        composable(route=Screens.signInScreen){
            SignInScreen(navController, loginViewModel)
        }
        composable(route=Screens.homeScreen){
            HomeScreen(navController)
        }
        composable(route=Screens.projectDetailsStep1Screen){
            ProjectDetailsStep1Screen(navController)
        }


        composable(
            route = "${Screens.projectFieldStep2Screen}/{projectName}/{projectLeader}",
            arguments = listOf(
                navArgument("projectName") { type = NavType.StringType },
                navArgument("projectLeader") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val projectName = backStackEntry.arguments?.getString("projectName")
            val projectLeader = backStackEntry.arguments?.getString("projectLeader")


            projectName?.let {
                projectLeader?.let {

                    ProjectFieldStep2Screen(navController, projectName, projectLeader)

                }
            }
        }

        composable(route=Screens.finishScreen){
            FinishScreen(navController)
        }
        composable(route = "${Screens.webViewScreen}?url={url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            url?.let {
                WebViewScreen(url = it, navController)
            }

        }

        composable(route = Screens.TermsAndPrivacyScreen) {
            TermsAndPrivacyScreen(navController)
        }

        composable(route=Screens.ForgotPasswordScreen){
            ForgotPasswordScreen(navController)
        }

    }
}
