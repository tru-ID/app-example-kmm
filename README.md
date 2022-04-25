# phonecheck-kmm-example



## Getting started

Before you begin, you will need:
1. To set up [an environment for Kotlin MultiPlatform] (https://kotlinlang.org/docs/multiplatform-mobile-setup.html):
    * For iOS: Xcode 11.3+ required, Cocoapods installed
    * For Android: Android Studio version 4.2 or 2020.3.1 Canary 8 or higher
    * Make sure that you have a compatible Kotlin plugin installed
    * Install the Kotlin Multiplatform Mobile plugin in Android Studio
    * Install Java
2. A mobile phone with mobile data connection.
3. To set up your environment for tru.ID PhoneCheck (https://developer.tru.id/docs/environment-setup)
    * Create a tru.ID account
    * Install tru.ID CLI
    * Run your local tunnel solution
    * Create a project
    * Run the development server, pointing to the directory containing the newly created project configuration

## Installation
This section is required if you want to use tru.ID Android and iOS SDKs within your own Kotlin MultiPlatform project.
For Android:
- Add the following to `repositories` in `buildscript` of the project `build.gradle.kts`

```
buildscript {
    repositories {
        maven (url = "https://gitlab.com/api/v4/projects/22035475/packages/maven")
}
}

```
- Add the latest version of the [tru-sdk-android] (https://github.com/tru-ID/tru-sdk-android) `build.gradle.kts` of the shared module as below:

```
kotlin {
sourceSets {
 val androidMain by getting {
            dependencies {
                implementation("id.tru.sdk:tru-sdk-android:0.3.3")
            }
        }
    }
}

```

For iOS:
- Add the latest version of the [tru-sdk-ios] (https://github.com/tru-ID/tru-sdk-ios) `build.gradle.kts` of the shared module as below:


```
kotlin {
cocoapods {

        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "12.0"

        pod("tru-sdk-ios") {
            version = "0.3.3"
            source = git ("https://github.com/tru-ID/tru-sdk-ios") { tag = "0.3.3"}
        }
        
        podfile = project.file("../PhoneCheckiOSApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
}

```

## Compatibility

- Minimum Android SDK: TruSDK requires a minimum API level of 21 (Android 5)

- Compile Android SDK: TruSDK requires you to compile against API 30  (Android 11) or later.

- Minimum deployment target for iOS is iOS 12

## Usage


## Run example
- Open the phonecheck-kmm-example folder with Android Studio.
- Update `SERVER_BASE_URL` within the APIService class of the commonMain Library at the shared module, to be the URL of your example server. Android Studio
- Connect your phone to your computer so it's used for running the phonecheck-kmm-example app. 
- Select either PhoneCheckAndroidApp or PhoneCheckiOSApp and Edit Configurations to further select your Execution target (i.e. your mobile phone)
- Run the application from your IDE. For iOS, you can also open the PhoneCheckiOSApp with Xcode.
- Enter the phone number for the mobile device in the UI in the format +{country_code}{number} e.g. +447900123456
- Press the done keyboard key or touch the "Verify my phone number" button
- You will see the result of the Phone Check

## Contributing

## Meta

Distributed under the MIT license. See `LICENSE` for more information.

[https://github.com/tru-ID](https://github.com/tru-ID)
[license-image]: https://img.shields.io/badge/License-MIT-blue.svg
[license-url]: LICENSE
