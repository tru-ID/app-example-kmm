package id.tru.kmm.phonecheckexample.android.util

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

fun isPhoneNumberValid(phoneNumber: String): Boolean {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()
    return try {
        phoneNumberUtil.isValidNumber(
            phoneNumberUtil.parse(
                phoneNumber, Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name
            )
        )
    } catch (e: NumberParseException) {
        false
    }
}