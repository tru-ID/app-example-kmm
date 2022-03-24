package id.tru.kmm.phonecheckexample
import cocoapods.tru_sdk_ios.*

import platform.UIKit.UIDevice

//Shared iOSMain
actual class Platform {
    actual suspend fun openCheckURL(checkURL: String): Boolean {
        return true
    }

    public actual final fun isReachable(): Boolean {
        return true
    }


}