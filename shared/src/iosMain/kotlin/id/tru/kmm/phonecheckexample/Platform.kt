package id.tru.kmm.phonecheckexample
import cocoapods.tru_sdk_ios.*
import platform.Foundation.NSURL
import platform.UIKit.UIDevice

//Shared iOSMain
actual class Platform {
    actual suspend fun openCheckURL(checkURL: String): Boolean {
        val sdk = TruSDK()
        val url = NSURL(string = "https://cnn.com")
//        sdk.checkWithTraceWithUrl(url)
        sdk.testFunctionWithUrl(url)
        return true
    }

    public actual final fun isReachable(): Boolean {
        return true
    }


}