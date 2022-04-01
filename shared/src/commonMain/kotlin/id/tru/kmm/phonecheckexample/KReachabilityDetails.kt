package id.tru.kmm.phonecheckexample

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class KReachabilityDetails(
    val error: KReachabilityError?,
    @SerialName("country_code") val countryCode: String,
    @SerialName("network_id") val networkId: String,
    @SerialName("network_name") val networkName: String,
    val products: ArrayList<KProduct>?,
    val link: String?
) {
    fun toJsonString(): String {
        return Json.encodeToString(this)
    }
}

