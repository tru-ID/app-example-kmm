package id.tru.kmm.phonecheckexample

import kotlinx.serialization.Serializable

@Serializable
data class KReachabilityError (
    val type: String?,
    val title: String?,
    val status: Int,
    val detail: String?,
    ) {

}