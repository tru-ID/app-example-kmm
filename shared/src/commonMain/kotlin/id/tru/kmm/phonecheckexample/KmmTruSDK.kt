package id.tru.kmm.phonecheckexample

//Shared commonMain
class KmmTruSDK(platform: Platform) {
    private val platform = platform

    @Throws(Exception::class) public final suspend fun openCheckURL(checkURL: String): Boolean {
        return true
    }

    @Throws(Exception::class) public final fun isReachable(): Boolean {
        return true
    }

}