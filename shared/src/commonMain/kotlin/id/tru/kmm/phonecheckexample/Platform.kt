package id.tru.kmm.phonecheckexample

//Shared commonMain
expect class Platform {

    @Throws(Exception::class)
    public final suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>?

    @Throws(Exception::class)
    public final suspend fun checkWithTrace(url: String): KTraceInfo

    @Throws(Exception::class)
    public final suspend fun isReachable(): KReachabilityDetails?

    @Throws(Exception::class)
    public final suspend fun isReachableWithDataResidency(dataResidency: String?): KReachabilityDetails?

}