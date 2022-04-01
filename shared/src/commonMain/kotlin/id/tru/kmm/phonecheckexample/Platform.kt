package id.tru.kmm.phonecheckexample
import java.net.URL

//Shared commonMain
expect class Platform {

    //Flutter SDK returns Map<Any?, Any?>?
    //Android SDK returns JSONObject?
    //iOS SDK returns Dictionary/Error in Closure
    public final suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>?
    public final suspend fun checkUrlWithResponseBody_Android(url: String): Map<Any?, Any?>?
    public final fun checkUrlWithResponseBody_iOS(url: String, completion: (Error?, Map<Any?, *>?) -> Unit)

    //Flutter SDK returns String?
    //Android SDK returns TraceInfo
    //iOS SDK returns TraceInfo/Error in Closure
    public final suspend fun checkWithTrace(url: String): String?
    @Throws(Exception::class)
    public final suspend fun checkWithTrace_Android(url: URL): KTraceInfo
    public final fun checkWithTrace_iOS(url: String, completion: (Error?, Map<Any?, *>?) -> Unit)

    //Flutter SDK returns String?
    //Android SDK returns ReachabilityDetails?
    //iOS SDK returns Result(ReachabilityDetails?/ReachabilityError) in Closure
    public final suspend fun isReachable(): String?
    public final suspend fun isReachable_Android(): KReachabilityDetails?
    public final fun isReachable_iOS(completion: (KReachabilityDetails?, KReachabilityError?) -> Unit)

    //Flutter SDK returns String?
    //Android SDK returns ReachabilityDetails?
    //iOS SDK returns Result(ReachabilityDetails?/ReachabilityError) in Closure
    public final suspend fun isReachableWithDataResidency(dataResidency: String?): String?
    public final suspend fun isReachableWithDataResidency_Android(dataResidency: String?): KReachabilityDetails?
    public final fun isReachable_iOS(dataResidency: String, completion: (KReachabilityDetails?, KReachabilityError?) -> Unit)

}