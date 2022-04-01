package id.tru.kmm.phonecheckexample

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tru.Id product (API)
 */
@Serializable
data class KProduct(
    @SerialName("product_id") val productId: String,
    @SerialName("product_name") val productName: String
)


