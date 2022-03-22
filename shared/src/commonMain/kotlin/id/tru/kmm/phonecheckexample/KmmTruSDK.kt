package id.tru.kmm.phonecheckexample
//import id.tru.sdk.network.TraceInfo

class KmmTruSDK(platform: Platform) {
    private val platform = platform

    @Throws(Exception::class) public final suspend fun openCheckURL(checkURL: String): Boolean {
        return platform.openCheckURL(checkURL)
    }

    @Throws(Exception::class) public final fun isReachable(): Boolean {
        return platform.isReachable()
    }

}