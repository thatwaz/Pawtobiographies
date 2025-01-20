// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false // For Android app modules
    alias(libs.plugins.jetbrains.kotlin.android) apply false // For Kotlin Android modules
    id("com.google.dagger.hilt.android") version "2.50" apply false
}
