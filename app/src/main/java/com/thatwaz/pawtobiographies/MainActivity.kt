package com.thatwaz.pawtobiographies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.thatwaz.pawtobiographies.ui.navigation.AppNavHost
import com.thatwaz.pawtobiographies.ui.theme.PawtobiographiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PawtobiographiesTheme {
                // Create a NavController instance
                val navController = rememberNavController()

                // Navigation host with the NavController
                AppNavHost(navController = navController)
            }
        }
    }
}



