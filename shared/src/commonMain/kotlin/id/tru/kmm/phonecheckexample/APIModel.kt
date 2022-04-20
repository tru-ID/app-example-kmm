package id.tru.kmm.phonecheckexample

import kotlinx.serialization.Serializable

@Serializable
data class PhoneCheckPost(
    val phone_number: String
)

@Serializable
data class PhoneCheck(
    val check_url: String,
    val check_id: String
)

@Serializable
data class PhoneCheckResult(
    val match: Boolean,
    val check_id: String
)