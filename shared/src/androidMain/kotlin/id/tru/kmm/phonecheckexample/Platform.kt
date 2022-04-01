package id.tru.kmm.phonecheckexample
import id.tru.sdk.*
import android.content.Context
import kotlinx.serialization.SerialName
import java.net.URL

// Shared androidMain
actual class Platform(private val context: Context)  {

    //Flutter SDK returns Map<Any?, Any?>?
    //Android SDK returns JSONObject?
    //iOS SDK returns Dictionary/Error in Closure
    @Throws(Exception::class)
    actual suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>? {
        return null
    }

    actual suspend fun checkUrlWithResponseBody_Android(url: String): Map<Any?, Any?>? {
        TruSDK.initializeSdk(context) //Need to optimise this.
        val truSdk = TruSDK.getInstance()
        val json = truSdk.checkUrlWithResponseBody(url)
        //turn json object to map
        return null
    }

    actual fun checkUrlWithResponseBody_iOS(url: String, completion: (Error?, Map<Any?, *>?) -> Unit) {
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
        TruSDK.initializeSdk(context) //Need to optimise this.
        val truSdk = TruSDK.getInstance()
        val traceInfo = truSdk.checkWithTrace(url)
        val kdebugInfo: KDebugInfo = KDebugInfo(traceInfo.debugInfo.toString())
        return KTraceInfo(
            trace = traceInfo.trace,
            debugInfo = kdebugInfo,
            responseBody = traceInfo.responseBody
        )
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
        TruSDK.initializeSdk(context) //Need to optimise this.
        val truSdk = TruSDK.getInstance()
        val reachabilityDetails = truSdk.isReachable()
        val kReachabilityError: KReachabilityError = KReachabilityError(reachabilityDetails?.error?.type,
            reachabilityDetails?.error?.title,
            reachabilityDetails?.error?.status!!, //TODO: find a better solution
            reachabilityDetails?.error?.detail)
        val product:Product? = reachabilityDetails.products?.get(0) //TODO: find a better solution
        val kProduct: KProduct? = KProduct(productId = product!!.productId!!, productName = product.productName)
        val products: ArrayList<Product>? = reachabilityDetails.products
        val kProducts: ArrayList<KProduct>? = ArrayList()
//        @SerialName("product_id") val productId: String,
//        @SerialName("product_name") val productName: String

            //turn reachabilityDetails object to KReachabilityDetails

        if (reachabilityDetails != null) {
            return KReachabilityDetails(
                error = kReachabilityError,
                countryCode = reachabilityDetails.countryCode,
                networkId = reachabilityDetails.networkId,
                networkName = reachabilityDetails.networkName,
                products = kProducts,
                link = reachabilityDetails.link
            )
        } else {
            return null
        }
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
        TruSDK.initializeSdk(context) //Need to optimise this.
        val truSdk = TruSDK.getInstance()
        val reachabilityDetails = truSdk.isReachable(dataResidency)
        //turn reachabilityDetails object to KreachabilityDetails
        val kReachabilityError: KReachabilityError = KReachabilityError(reachabilityDetails?.error?.type,
            reachabilityDetails?.error?.title,
            reachabilityDetails?.error?.status!!,
            reachabilityDetails?.error?.detail)
        val product:Product? = reachabilityDetails.products?.get(0)
        val kProduct: KProduct? = KProduct(productId = product!!.productId!!, productName = product.productName)
        val products: ArrayList<Product>? = reachabilityDetails.products
        val kProducts: ArrayList<KProduct>? = ArrayList()
//        @SerialName("product_id") val productId: String,
//        @SerialName("product_name") val productName: String

        //turn reachabilityDetails object to KReachabilityDetails

        if (reachabilityDetails != null) {
            return KReachabilityDetails(
                error = kReachabilityError,
                countryCode = reachabilityDetails.countryCode,
                networkId = reachabilityDetails.networkId,
                networkName = reachabilityDetails.networkName,
                products = kProducts,
                link = reachabilityDetails.link
            )
        } else {
            return null
        }
    }

    actual fun isReachable_iOS(
        dataResidency: String,
        completion: (KReachabilityDetails?, KReachabilityError?) -> Unit
    ) {
        completion(null, null)
    }

}