package id.tru.kmm.phonecheckexample
import cocoapods.playground.TraceInfo
import cocoapods.playground.TruSDK
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.yield
import platform.Foundation.NSError
import platform.Foundation.NSURL
import kotlin.time.Duration

//Shared iOSMain
actual class Platform {

    private val truSDK: TruSDK = TruSDK()
    init {

    }

    @Throws(Exception::class)
    actual final suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>? {
        val mutex = Mutex()
        val url = NSURL(string = url)
        println("KMM: checkWithTrace called with $url")
        var nError: NSError? = null
        var nMap: Map<Any?, *>? = null
        fun protoClosure(error:NSError?, map: Map<Any?, *>?): Unit {
            println("KMM: protoClosure has been called.")
            nError = error
            nMap = map
            println("KMM: Before UnLock")
            mutex.unlock()
        }
        mutex.lock()
        truSDK.checkUrlWithResponseBodyWithUrl(url = url, ::protoClosure)
        println("Is locked? $mutex.isLocked")
        mutex.withLock {
            println("KMM: WithLock")
            println("KMM: Results: $nError - $nMap")
            return nMap
        }
    }

    @Throws(Exception::class)
    actual final suspend fun checkWithTrace(url: String): KTraceInfo {
        print("KMM: checkWithTrace called with $url")
        val mutex = Mutex()
        var nError: NSError? = null
        var nTraceInfo: TraceInfo? = null
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
            return KTraceInfo("", debugInfo = info, responseBody = "")
        }
    }

    @Throws(Exception::class)
    actual final suspend fun isReachable(): KReachabilityDetails? {
        print("isReachable called.")
        val mutex = Mutex()
        var nError: KReachabilityError? = null
        var nDetails: KReachabilityDetails? = null
        fun protoClosure(details: KReachabilityDetails?, error: KReachabilityError?) {
            println("KMM: protoClosure has been called.")
            println("KMM: Before UnLock")
            nError = error
            nDetails = details
            mutex.unlock()
        }
        mutex.lock()
        //TODO: Playground project updated, needs publishing
        //truSDK.isReachable(::protoClosure)
        println("KMM: Is locked? $mutex.isLocked")
        mutex.withLock {
            // Use nDetails to populate the below
            return KReachabilityDetails(
                error = null,
                countryCode = "reachabilityDetails.countryCode",
                networkId = "reachabilityDetails.networkId",
                networkName = "reachabilityDetails.networkName",
                products = null,
                link = "reachabilityDetails.link"
            )
        }
    }

    @Throws(Exception::class)
    actual final suspend fun isReachableWithDataResidency(dataResidency: String?): KReachabilityDetails? {
        print("isReachableWithDataResidency called.")
        val mutex = Mutex()
        var nError: KReachabilityError? = null
        var nDetails: KReachabilityDetails? = null
        fun protoClosure(details: KReachabilityDetails?, error: KReachabilityError?) {
            println("KMM: protoClosure has been called.")
            println("KMM: Before UnLock")
            nError = error
            nDetails = details
            mutex.unlock()
        }
        mutex.lock()
        //TODO: Playground project updated, needs publishing
        //truSDK.isReachable(dataResidency = dataResidency, ::protoClosure)
        println("KMM: Is locked? $mutex.isLocked")
        mutex.withLock {
            // Use nDetails to populate the below
            return KReachabilityDetails(
                error = null,
                countryCode = "reachabilityDetails.countryCode",
                networkId = "reachabilityDetails.networkId",
                networkName = "reachabilityDetails.networkName",
                products = null,
                link = "reachabilityDetails.link"
            )
        }
    }

}