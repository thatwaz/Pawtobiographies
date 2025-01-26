package com.thatwaz.pawtobiographies.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thatwaz.pawtobiographies.data.local.entities.Pet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPetInfoScreen(
    onSavePetInfo: (Pet) -> Unit, // Callback to save pet info
    onCancel: () -> Unit
) {
    val petName = remember { mutableStateOf("") }
    val petType = remember { mutableStateOf("") }
    val petAge = remember { mutableStateOf("") }
    val petImageUri = remember { mutableStateOf<Uri?>(null) }
    val isMyPet = remember { mutableStateOf(true) } // Default to "My Pet"

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val uri = result.data?.data
        if (uri != null) {
            petImageUri.value = uri // Save the URI of the selected image
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Pet Info") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = petName.value,
                onValueChange = { petName.value = it },
                label = { Text("Pet Name") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = petType.value,
                onValueChange = { petType.value = it },
                label = { Text("Pet Type (e.g., Dog, Cat)") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = petAge.value,
                onValueChange = { petAge.value = it },
                label = { Text("Pet Age") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    // Launch Google Photos directly with the appropriate intent
                    val intent = Intent(Intent.ACTION_PICK).apply {
                        type = "image/*"
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        setPackage("com.google.android.apps.photos") // Specifically target Google Photos
                    }
                    pickImageLauncher.launch(intent)
                }
            ) {
                Text("Choose Profile Picture")
            }
            petImageUri.value?.let {
                Text("Selected Image URI: $it")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Is this your pet?")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isMyPet.value,
                    onCheckedChange = { isMyPet.value = it }
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onSavePetInfo(
                            Pet(
                                id = 0, // Auto-generated
                                name = petName.value,
                                type = petType.value,
                                age = petAge.value,
                                imageUri = petImageUri.value.toString(),
                                isMyPet = isMyPet.value
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }
                Button(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}









