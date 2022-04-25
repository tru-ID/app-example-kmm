# phonecheck-kmm-example



## Getting started

Before you begin, you will need to :
1. set up [an environment for Kotlin MultiPlatform] (https://kotlinlang.org/docs/multiplatform-mobile-setup.html):
    * For iOS: Xcode 11.3+ required
    * For Android: Android Studio version 4.2 or 2020.3.1 Canary 8 or higher
    * Make sure that you have a compatible Kotlin plugin installed
    * Install the Kotlin Multiplatform Mobile plugin in Android Studio
    * Install Java
2. A mobile phone with mobile data connection.

## Installation

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

## Contributing

## Meta

Distributed under the MIT license. See `LICENSE` for more information.

[https://github.com/tru-ID](https://github.com/tru-ID)
[license-image]: https://img.shields.io/badge/License-MIT-blue.svg
[license-url]: LICENSE
