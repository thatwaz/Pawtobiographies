package com.thatwaz.pawtobiographies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun HomeScreen(
    myPets: List<Pet>, // List of your pets
    friendsPets: List<Pet>, // List of friends' pets
    onAddPet: () -> Unit, // Action for adding your pet
    onPetClick: (Pet) -> Unit // Action for navigating to pet details
) {
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
        LazyColumn(
            contentPadding = PaddingValues(
                start = 8.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                end = 8.dp,
                bottom = paddingValues.calculateBottomPadding() + 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Space between sections
        ) {
            // Section for My Pets
            item {
                Text(
                    text = "My Pets",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            items(myPets) { pet ->
                PetCard(pet, onPetClick)
            }

            // Section for Friends' Pets
            if (friendsPets.isNotEmpty()) {
                item {
                    Text(
                        text = "Pawesome Friends", // Fun title for friends' pets
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                items(friendsPets) { pet ->
                    PetCard(pet, onPetClick)
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





//// Preview to visualize the mock UI
//@Composable
//@Preview(showBackground = true)
//fun PreviewHomeScreen() {
//    HomeScreen(
//        pets = mockPets,
//        onAddPet = { /* No-op */ },
//        onPetClick = { /* No-op */ }
//    )
//}

