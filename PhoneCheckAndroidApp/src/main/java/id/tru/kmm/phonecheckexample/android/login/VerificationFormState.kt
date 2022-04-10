package id.tru.kmm.phonecheckexample.android.login

/**
 * Data validation state of the login form.
 */
data class VerificationFormState(
    val phoneNumberError: Int? = null,
    val tcNotAcceptedError: Int? = null,
    val isDataValid: Boolean = false
)