package id.tru.kmm.phonecheckexample
import cocoapods.tru_sdk_ios.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import platform.Foundation.NSError
import platform.Foundation.NSURL

//Shared iOSMain
actual class Platform {

    private val truSDK: ObjcTruSDK = ObjcTruSDK()

    @Throws(Exception::class)
    actual final suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>? {
        var nMap: Map<Any?, *>? = null
        try {
            val mutex = Mutex()
            val url = NSURL(string = url)
            println("2 - KMM: checkWithTrace called with $url")
            var nError: NSError? = null

            fun protoClosure(error:NSError?, map: Map<Any?, *>?): Unit {
                println("4 - KMM: protoClosure has been called.")
                nError = error
                nMap = map
                println("5 - KMM: Before UnLock")
                mutex.unlock()
        }
        mutex.lock()
        truSDK.checkUrlWithResponseBodyWithUrl(url = url, ::protoClosure)
        println("3 - Is locked? $mutex.isLocked")
        mutex.withLock {
            println("6 - KMM: WithLock")
            println("7 - KMM: Results: $nError - $nMap")
            return nMap
        }
    }catch (e: Exception) {
        throw Exception("Something went wrong")
    }}

    @Throws(Exception::class)
    actual final suspend fun checkWithTrace(url: String): KTraceInfo {
        print("KMM: checkWithTrace called with $url")
        var nTraceInfo: TraceInfo? = null
        try {
        val mutex = Mutex()
        var nError: NSError? = null

        fun protoClosure(error: NSError?, traceInfo: TraceInfo?) {
            println("KMM: protoClosure has been called.")
            nError = error
            nTraceInfo = traceInfo
            println("KMM: Before UnLock")
            mutex.unlock()
        }
        mutex.lock()
        truSDK.checkWithTraceWithUrl(NSURL(string = url), ::protoClosure)
        println("KMM: Is locked? $mutex.isLocked")
        mutex.withLock {
            println("KMM: WithLock")
            val info: KDebugInfo = KDebugInfo("")
            println("KMM: Results: $nError - $nTraceInfo")
            return KTraceInfo(
                trace = nTraceInfo?.trace() ?: "" ,
                debugInfo = info,
                responseBody = nTraceInfo?.responseBody().toString())
        }
    } catch (e: Exception) {
        throw Exception("Something went wrong")
    }}

    @Throws(Exception::class)
    actual final suspend fun isReachable(): KReachabilityDetails? {
        print("isReachable called.")
        var nDetails: KReachabilityDetails? = null
        try {
        val mutex = Mutex()
        fun protoClosure(details: ReachabilityDetails?, error: ReachabilityError?) {
            println("KMM: protoClosure has been called.")
            println("KMM: Before UnLock")
            var nError : KReachabilityError? = null
            error?.let {
                nError = KReachabilityError(
                    it.type(),
                    it.title(),
                    it.status() as Long,
                    it.detail())
            }
            println("KMM: Reachability Error created")
            var kProducts: ArrayList<KProduct> = ArrayList()
            details?.let {
                it.products()?.let {
                    for (product in it) {
                        val p = KProduct(productId = (product as Product).productId(),
                            productName = (product as Product).productName())
                        kProducts.add(p)
                    }
                    println("KMM: for loop complete")
                }
            }
            println("KMM: Listed products")
            nDetails = KReachabilityDetails(
                error = nError,
                countryCode = details?.countryCode() ?: "",
                networkId = details?.networkId() ?: "",
                networkName = details?.networkName() ?: "",
                products = kProducts,
                link = ""
            )
            println("KMM: Unlocking")
            mutex.unlock()
        }

        mutex.lock()
        truSDK.isReachableWithCompletion(::protoClosure)
        println("KMM: Is locked? $mutex.isLocked")
        mutex.withLock {
            return nDetails
        }
    } catch (e: Exception) {
            throw Exception("Something went wrong")
    }}

    @Throws(Exception::class)
    actual final suspend fun isReachableWithDataResidency(dataResidency: String?): KReachabilityDetails? {
        print("isReachableWithDataResidency called.")
        val mutex = Mutex()
        var nDetails: KReachabilityDetails? = null
        fun protoClosure(details: ReachabilityDetails?, error: ReachabilityError?) {
            println("KMM: protoClosure has been called.")
            println("KMM: Before UnLock")
            var nError : KReachabilityError? = null
            error?.let {
                nError = KReachabilityError(
                    it.type(),
                    it.title(),
                   it.status() as Long,
                   it.detail())
            }
            println("KMM: Reachability Error created")
            var kProducts: ArrayList<KProduct> = ArrayList()
            details?.let {
                it.products()?.let {
                    for (product in it) {
                        val p = KProduct(productId = (product as Product).productId(), productName = (product as Product).productName())
                        kProducts.add(p)
                    }
                    println("KMM: for loop complete")
                }

            }
            println("KMM: Listed products")
            nDetails = KReachabilityDetails(
                error = nError,
                countryCode = details?.countryCode() ?: "",
                networkId = details?.networkId() ?: "",
                networkName = details?.networkName() ?: "",
                products = kProducts,
                link = ""
            )
            println("KMM: Unlocking")
            mutex.unlock()
        }
        mutex.lock()
        truSDK.isReachableWithDataResidency(dataResidency = dataResidency, ::protoClosure)
        println("KMM: Is locked? $mutex.isLocked")

        mutex.withLock {
            return nDetails
        }
    }

}