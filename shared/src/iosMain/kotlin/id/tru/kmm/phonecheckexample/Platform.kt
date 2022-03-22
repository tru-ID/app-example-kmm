package id.tru.kmm.phonecheckexample

import platform.UIKit.UIDevice

//Shared iOSMain
actual class Platform {
    actual suspend fun openCheckURL(checkURL: String): Boolean {
        TODO("Not yet implemented")
    }

    public actual final fun isReachable(): Boolean {
        return true
    }

}