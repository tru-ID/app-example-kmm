package id.tru.kmm.phonecheckexample
import id.tru.sdk.TruSDK
import android.content.Context

// Shared androidMain
actual class Platform(private val context: Context)  {

    @Throws(Exception::class)
    public actual final suspend fun openCheckURL(checkURL: String): Boolean {
        TruSDK.initializeSdk(context) //Need to optimise this.
        val truSdk = TruSDK.getInstance()
        return truSdk.openCheckUrl(checkURL)
        return true
    }

    public actual final fun isReachable(): Boolean {
        return true
    }
    //Problem 1: How to expose TraceInfo etc classes.
    //Problem 2: How to expose JSONObject?
    //Potentially convert to HashMap (and therefore Dictionary in Swift)
}