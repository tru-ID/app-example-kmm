package id.tru.kmm.phonecheckexample.android.model

import id.tru.kmm.phonecheckexample.android.login.VerifiedPhoneNumberView

/**
 * Phone Check verification result : success or error message.
 */
data class VerificationCheckResult(
    val success: VerifiedPhoneNumberView? = null,
    val progressUpdate: Triple<Step, Int, Boolean>? = null,
    val error: Int? = null
)

enum class Step {
    FIRST, SECOND, THIRD, FOURTH
}