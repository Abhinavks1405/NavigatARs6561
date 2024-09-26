# NavigatAR

**NavigatAR** is an indoor navigation system using Augmented Reality (AR) technology, designed to assist users in navigating complex indoor spaces such as railway stations or educational institutions. Built using Unity and AR Foundation, it overlays digital navigation paths onto real-world environments to provide real-time directions.

## Features

- **AR-Based Navigation:** Provides step-by-step navigation using AR markers and 3D paths.
- **Voice Guidance:** Offers audio directions based on the user's location and orientation.
- **Customizable Routes:** Easily configure paths and destinations for different locations.
- **Interactive UI:** Intuitive user interface for selecting destinations and viewing navigation information.
- **Smooth Integration:** Utilizes AR Foundation and Unity's built-in features for a seamless user experience.

## Technology Stack

- **Unity:** The core development platform for creating the AR experience.
- **AR Foundation:** A unified framework to build AR experiences across different platforms.
- **Google ARCore:** Used for AR capabilities on Android devices.
- **NavMesh:** For pathfinding and creating navigable areas within the environment.
- **C# Scripting:** For custom functionalities and logic implementation.

## Setup Instructions

### Prerequisites

- Unity 2020.3 or later
- Android Studio 4.0 or later
- JDK 8 or later
- Android SDK API level 24 or higher

### Unity Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/NavigatAR.git

### Unity Setup

1. Open the project in **Unity Hub**.

2. Ensure **AR Foundation** and **ARCore XR Plugin** packages are installed via the **Package Manager**.

3. Set up the build settings for Android:
   - Go to **File > Build Settings**
   - Switch platform to **Android**
   - Click on **Player Settings** and configure the following:
     - Set **Company Name** and **Product Name**
     - In **Other Settings**, set **Scripting Backend** to `IL2CPP`
     - Set **Target API Level** to `Android 7.0` or higher
    
### Android Studio Setup

1. In **Unity**, build the project for Android:
   - Go to **File > Build Settings**
   - Click **Build**
   - Choose a location to save the Android project

2. Open **Android Studio** and select "Open an existing Android Studio project".

3. Navigate to the folder where you saved the Android project and select it.

4. Ensure you have the following in your `build.gradle` file:
   ```gradle
   android {
       compileSdkVersion 29
       defaultConfig {
           minSdkVersion 26
           targetSdkVersion 34
       }
   }
5. Sync the project with **Gradle** files.

6. Connect an AR-capable Android device to your computer.

7. Click on **Run** to build and install the app on your device.

## Usage

1. Launch the **NavigatAR** app on your AR-capable Android device.

2. Allow the app to access your camera and location.

3. Select your destination from the available options.

4. Follow the AR overlay and voice guidance to navigate to your chosen destination.

## Contributing

We welcome contributions to **NavigatAR**! Please read our `CONTRIBUTING.md` file for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License – see the `LICENSE.md` file for details.

## Acknowledgements

- **Unity** and **AR Foundation** for providing robust tools for AR development.
- **Google ARCore** for enabling AR functionalities on Android devices.
- **NavMesh** for efficient pathfinding and navigation capabilities within Unity.


