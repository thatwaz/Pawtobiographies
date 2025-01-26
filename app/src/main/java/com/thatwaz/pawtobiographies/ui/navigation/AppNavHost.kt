package com.thatwaz.pawtobiographies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thatwaz.pawtobiographies.ui.screens.AddPetInfoScreen
import com.thatwaz.pawtobiographies.ui.screens.HomeScreen
import com.thatwaz.pawtobiographies.ui.viewmodel.PetViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: PetViewModel = hiltViewModel() // Shared ViewModel injected using Hilt
) {
    NavHost(navController = navController, startDestination = "home") {
        // Home screen
        composable("home") {
            HomeScreen(
                onAddPet = { navController.navigate("add_pet") }, // Navigate to AddPetInfoScreen
                onPetClick = { pet ->
                    // Handle pet click here
                },
                viewModel = viewModel // Pass ViewModel to HomeScreen
            )
        }

        // Add Pet Info screen
        composable("add_pet") {
            AddPetInfoScreen(
                onSavePetInfo = { pet ->
                    // Save the pet info using ViewModel
                    viewModel.addPet(pet)
                    // Navigate back to the HomeScreen
                    navController.popBackStack()
                },
                onCancel = {
                    // Navigate back to the HomeScreen
                    navController.popBackStack()
                }
            )
        }
    }
}





