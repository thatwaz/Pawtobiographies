package com.thatwaz.pawtobiographies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thatwaz.pawtobiographies.R

// Mock data class for pets
//data class Pet(val id: Int, val name: String, val age: String)
data class Pet(val id: Int, val name: String, val age: String, val imageResId: Int)


// Mock list of pets
val mockPets = listOf(
    Pet(2, "Jazzi", "12 years", R.drawable.jazzi),
    Pet(3, "Dippers", "15 years", R.drawable.dippers)
)


@Composable
fun HomeScreen(pets: List<Pet>, onAddPet: () -> Unit, onPetClick: (Pet) -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddPet) {
                Icon(Icons.Default.Add, contentDescription = "Add Pet")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "My Pets",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )

            if (pets.isEmpty()) {
                Text(
                    text = "No pets yet! Add your first pet to get started.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                Column {
                    pets.forEach { pet ->
                        PetCard(pet, onPetClick)
                    }
                }
            }
        }
    }
}

@Composable
fun PetCard(pet: Pet, onClick: (Pet) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Add some space around the card
            .clickable { onClick(pet) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp) // Internal padding within the card
        ) {
            Image(
                painter = painterResource(id = pet.imageResId),
                contentDescription = "Photo of ${pet.name}",
                modifier = Modifier
                    .size(100.dp) // Increase the size of the image
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.headlineMedium // Use larger text for the name
                )
                Text(
                    text = "Age: ${pet.age}",
                    style = MaterialTheme.typography.bodyLarge // Adjust size for secondary text
                )
            }
        }
    }
}



// Preview to visualize the mock UI
@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen(
        pets = mockPets,
        onAddPet = { /* No-op */ },
        onPetClick = { /* No-op */ }
    )
}

