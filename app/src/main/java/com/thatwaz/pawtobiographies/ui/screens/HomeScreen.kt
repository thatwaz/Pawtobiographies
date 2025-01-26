package com.thatwaz.pawtobiographies.ui.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thatwaz.pawtobiographies.R
import com.thatwaz.pawtobiographies.data.local.entities.Pet
import com.thatwaz.pawtobiographies.ui.viewmodel.PetViewModel


@Composable
fun HomeScreen(
    onAddPet: () -> Unit,
    onPetClick: (Pet) -> Unit,
    viewModel: PetViewModel = hiltViewModel()
) {
    val myPets = viewModel.myPets.collectAsState(initial = emptyList())
    val friendsPets = viewModel.friendsPets.collectAsState(initial = emptyList())

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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Temporary button to delete all pets
            Button(onClick = { viewModel.deleteAllPets() }) {
                Text("Delete All Profiles")
            }

            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "My Pets",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(myPets.value) { pet ->
                    PetCard(pet, onPetClick)
                }

                if (friendsPets.value.isNotEmpty()) {
                    item {
                        Text(
                            text = "Pawesome Friends",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(friendsPets.value) { pet ->
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
            .padding(horizontal = 8.dp)
            .clickable { onClick(pet) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pet.imageUri) // Pass the URI
                    .crossfade(true) // Optional crossfade animation
                    .error(R.drawable.dippers) // Fallback image if loading fails
                    .build(),
                contentDescription = "Photo of ${pet.name}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Age: ${pet.age}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}





//@Composable
//fun PetCard(pet: Pet, onClick: (Pet) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp)
//            .clickable { onClick(pet) },
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface,
//            contentColor = MaterialTheme.colorScheme.onSurface
//        ),
//        elevation = CardDefaults.cardElevation(8.dp)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .padding(16.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.dippers), // Placeholder for now
//                contentDescription = "Photo of ${pet.name}",
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(CircleShape)
//                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                Text(
//                    text = pet.name,
//                    style = MaterialTheme.typography.headlineMedium,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//                Text(
//                    text = "Age: ${pet.age}",
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//            }
//        }
//    }
//}






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

