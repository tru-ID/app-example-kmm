package id.tru.kmm.phonecheckexample.android.login

/**
 * Phone Check details that is exposed to the UI
 */
data class VerifiedPhoneNumberView(
    val phoneNumber: String,
    val checkId: String,
    val match: Boolean
)