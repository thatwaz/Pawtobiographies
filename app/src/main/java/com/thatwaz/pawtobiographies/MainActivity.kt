package com.thatwaz.pawtobiographies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thatwaz.pawtobiographies.ui.screens.HomeScreen
import com.thatwaz.pawtobiographies.ui.screens.Pet
import com.thatwaz.pawtobiographies.ui.theme.PawtobiographiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PawtobiographiesTheme {
                // Mock lists for demonstration
                val myPets = listOf(
                    Pet(1, "Jazzi", "12 years", R.drawable.jazzi),
                    Pet(2, "Dippers", "15 years", R.drawable.dippers)
                )
                val friendsPets = listOf(
                    Pet(3, "Buddy", "7 years", R.drawable.jazzi),
                    Pet(4, "Luna", "5 years", R.drawable.dippers)
                )

                // HomeScreen composable
                HomeScreen(
                    myPets = myPets,
                    friendsPets = friendsPets,
                    onAddPet = {
                        // Logic to add a new pet
                        println("Add pet button clicked")
                    },
                    onPetClick = { pet ->
                        println("Clicked on: ${pet.name}")
                        // Navigate to detailed pet bio (future functionality)
                    }
                )
            }
        }
    }
}


