// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Applying alias for android application plugin
    alias(libs.plugins.android.application) apply false

    // Applying alias for google services plugin
    alias(libs.plugins.google.gms.google.services) apply false

    // Using the correct version of KSP plugin
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}

