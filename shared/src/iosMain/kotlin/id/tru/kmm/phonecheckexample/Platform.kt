package id.tru.kmm.phonecheckexample
import cocoapods.playground.*
import platform.Foundation.NSError
import platform.Foundation.NSURL
import java.net.URL

//Shared iOSMain
actual class Platform {

    fun testClosure(error:NSError?, map: Map<Any?, *>?): Unit {
        print("hello")
    }

    //Flutter SDK returns Map<Any?, Any?>?
    //Android SDK returns JSONObject?
    //iOS SDK returns Dictionary/Error in Closure
    actual suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>? {
        return null
    }

    actual suspend fun checkUrlWithResponseBody_Android(url: String): Map<Any?, Any?>? {
        return null
    }

    actual fun checkUrlWithResponseBody_iOS(url: String, completion: (Error?, Map<Any?, *>?) -> Unit) {
        val sdk = TruSDK()
        val url = NSURL(string = "https://cnn.com")
        sdk.testFunctionWithUrl(url)
        sdk.checkUrlWithResponseBodyWithUrl(url = url, ::testClosure)
        completion(Error("This is an Error"), null)
    }

    //Flutter SDK returns String?
    //Android SDK returns TraceInfo
    //iOS SDK returns TraceInfo/Error in Closure
    actual suspend fun checkWithTrace(url: String): String? {
        return null
    }

    @Throws(Exception::class)
    actual suspend fun checkWithTrace_Android(url: URL): KTraceInfo {
        return KTraceInfo()
    }

    actual fun checkWithTrace_iOS(url: String, completion: (Error?, Map<Any?, *>?) -> Unit) {
        completion(null, null)
    }

    //Flutter SDK returns String?
    //Android SDK returns ReachabilityDetails?
    //iOS SDK returns Result(ReachabilityDetails?/ReachabilityError) in Closure
    actual suspend fun isReachable(): String? {
        return null
    }

    actual suspend fun isReachable_Android(): KReachabilityDetails? {
        return null
    }

    actual fun isReachable_iOS(completion: (KReachabilityDetails?, KReachabilityError?) -> Unit) {
        completion(null, null)
    }

    //Flutter SDK returns String?
    //Android SDK returns ReachabilityDetails?
    //iOS SDK returns Result(ReachabilityDetails?/ReachabilityError) in Closure
    actual suspend fun isReachableWithDataResidency(dataResidency: String?): String? {
        return null
    }

    actual suspend fun isReachableWithDataResidency_Android(dataResidency: String?): KReachabilityDetails? {
        return null
    }

    actual fun isReachable_iOS(
        dataResidency: String,
        completion: (KReachabilityDetails?, KReachabilityError?) -> Unit
    ) {
        completion(null, null)
    }

}