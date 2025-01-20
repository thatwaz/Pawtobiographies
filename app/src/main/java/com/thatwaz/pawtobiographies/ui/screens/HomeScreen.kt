package com.thatwaz.pawtobiographies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            FloatingActionButton(
                onClick = onAddPet,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Pet")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background) // Set background color
        ) {
            Text(
                text = "My Pets",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary, // Use primary color
                modifier = Modifier.padding(16.dp)
            )

            if (pets.isEmpty()) {
                Text(
                    text = "No pets yet! Add your first pet to get started.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground, // Use onBackground color
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp), // Padding around the list
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Space between cards
                ) {
                    items(pets) { pet ->
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
            .padding(horizontal = 8.dp) // Horizontal padding for better spacing
            .clickable { onClick(pet) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface, // Card background color
            contentColor = MaterialTheme.colorScheme.onSurface // Card content color
        ),
        elevation = CardDefaults.cardElevation(8.dp) // Add elevation for shadow effect
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = pet.imageResId),
                contentDescription = "Photo of ${pet.name}",
                modifier = Modifier
                    .size(80.dp) // Reduce the size slightly for balance
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape) // Add border with primary color
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface // Text color for headline
                )
                Text(
                    text = "Age: ${pet.age}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface // Text color for body
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

