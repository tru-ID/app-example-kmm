package id.tru.kmm.phonecheckexample

//Shared commonMain
expect class Platform {
    //Deprecated

    public final suspend fun openCheckURL(checkURL: String): Boolean
    public final fun isReachable(): Boolean
//    public final suspend fun checkUrlWithResponseBody(checkUrl: String): org.json.JSONObject
//    public final suspend fun checkWithTrace(url: java.net.URL): TraceInfo
//    @Throws(Exception::class)
//    public final fun isReachable():ReachabilityDetails?
//    public final fun isReachable(dataResidency: String?):ReachabilityDetails?
}