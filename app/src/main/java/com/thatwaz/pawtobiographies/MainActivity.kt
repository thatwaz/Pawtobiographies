package com.thatwaz.pawtobiographies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thatwaz.pawtobiographies.ui.screens.HomeScreen
import com.thatwaz.pawtobiographies.ui.screens.mockPets
import com.thatwaz.pawtobiographies.ui.theme.PawtobiographiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PawtobiographiesTheme {
                // Pass the mockPets list here
                HomeScreen(
                    pets = mockPets,
                    onAddPet = { /* Add pet logic */ },
                    onPetClick = { pet -> println("Clicked on: ${pet.name}") }
                )
            }
        }
    }
}

