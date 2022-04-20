package id.tru.kmm.phonecheckexample
import id.tru.sdk.*
import android.content.Context
import java.net.URL

// Shared androidMain
actual class Platform(private val context: Context)  {
    private val truSDK: TruSDK
    init {
        TruSDK.initializeSdk(context)
        this.truSDK = TruSDK.getInstance()
    }

    @Throws(Exception::class)
    actual final suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>? {
        val json = this.truSDK.checkUrlWithResponseBody(url)
            if (json != null) {
                return if (json.has("code") && json.has("check_id")) {
                    mapOf("code" to json["code"])
                            as? Map<Any?, Any?>
                } else if (json.has("error") && json.has("error_description")) {
                    mapOf(
                        "error" to json["error"],
                        "error_description" to json["error_description"],
                        "check_id" to json["check_id"],
                        "reference_id" to json["reference_id"]
                    )
                            as? Map<Any?, Any?>
                } else {
                    error("There is an issue with response body. Unable to serialise success or error from the dictionary")
                }
            } else {
                return emptyMap()
            }
    }

    @Throws(Exception::class)
    actual final suspend fun checkWithTrace(url: String): KTraceInfo {
        val kurl = URL(url)
        val traceInfo = this.truSDK.checkWithTrace(kurl)
        val kDebugInfo: KDebugInfo = KDebugInfo(traceInfo.debugInfo.toString())
        return KTraceInfo(
            trace = traceInfo.trace,
            debugInfo = kDebugInfo,
            responseBody = traceInfo.responseBody.toString()
        )
    }

    @Throws(Exception::class)
    actual final suspend fun isReachable(): KReachabilityDetails? {
        val reachabilityDetails = this.truSDK.isReachable()
        val kReachabilityError: KReachabilityError = KReachabilityError(reachabilityDetails?.error?.type,
            reachabilityDetails?.error?.title,
            reachabilityDetails?.error?.status as Long,
            reachabilityDetails?.error?.detail)

        val kProducts: ArrayList<KProduct> = ArrayList()
        reachabilityDetails?.let {
            it.products?.let {
                for (product in it) {
                    val kProduct: KProduct = KProduct(productId = product.productId, productName = product.productName)
                    kProducts.add(kProduct)
                }
            }
        }


        if (reachabilityDetails != null) return KReachabilityDetails(
            error = kReachabilityError,
            countryCode = reachabilityDetails.countryCode,
            networkId = reachabilityDetails.networkId,
            networkName = reachabilityDetails.networkName,
            products = kProducts,
            link = reachabilityDetails.link
        ) else return null
    }

    @Throws(Exception::class)
    actual final suspend fun isReachableWithDataResidency(dataResidency: String?): KReachabilityDetails? {
        val reachabilityDetails = this.truSDK.isReachable(dataResidency)
        val kReachabilityError: KReachabilityError = KReachabilityError(reachabilityDetails?.error?.type,
            reachabilityDetails?.error?.title,
            reachabilityDetails?.error?.status as Long,
            reachabilityDetails?.error?.detail)
        val products: ArrayList<Product>? = reachabilityDetails.products
        val kProducts: ArrayList<KProduct>? = ArrayList()
        if (products != null) {
            for (product in products) {
                val kProduct: KProduct = KProduct(productId = product.productId, productName = product.productName)
                kProducts?.add(kProduct)
            }
        }
        return if (reachabilityDetails != null) {
            KReachabilityDetails(
                error = kReachabilityError,
                countryCode = reachabilityDetails.countryCode,
                networkId = reachabilityDetails.networkId,
                networkName = reachabilityDetails.networkName,
                products = kProducts,
                link = reachabilityDetails.link
            )
        } else {
            null
        }
    }

}