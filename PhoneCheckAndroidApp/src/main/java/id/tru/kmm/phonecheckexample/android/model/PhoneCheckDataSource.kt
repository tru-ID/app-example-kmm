package id.tru.kmm.phonecheckexample.android.model
import android.content.Context
import android.util.Log
import id.tru.kmm.phonecheckexample.APIService
import id.tru.kmm.phonecheckexample.KReachabilityDetails
import id.tru.kmm.phonecheckexample.KTraceInfo
import id.tru.kmm.phonecheckexample.Platform
import id.tru.kmm.phonecheckexample.PhoneCheckPost
import id.tru.kmm.phonecheckexample.PhoneCheck
import id.tru.kmm.phonecheckexample.PhoneCheckResult
import java.io.IOException

/**
 * Class that handles phone check
 */
class PhoneCheckDataSource(applicationContext: Context) {
    private val sdk = Platform(context = applicationContext)
    private val apiService = APIService()

    // Step 1: Create a Phone Check
    @Throws(Exception::class)
    suspend fun createPhoneCheck(phone: String): Result<PhoneCheck> {
        val phoneCheck = apiService.getPhoneCheck(PhoneCheckPost(phone))
        return if (phoneCheck != null) {
            Result.Success(phoneCheck)
        } else {
            Result.Error(IOException("Error Occurred: Response is not valid"))
        }
    }

    // Step 2: Check URL
    @Throws(Exception::class)
    suspend fun checkUrlWithResponseBody(url: String): Map<Any?, Any?>? {
        Log.d("TruSDK", "Triggering open check url $url")
        return sdk.checkUrlWithResponseBody(url)
    }

    // Step 3: Get Phone Check Result
    @Throws(Exception::class)
    suspend fun retrievePhoneCheckResult(checkID: String): Result<PhoneCheckResult> {
        val phoneCheckResult = apiService.getPhoneCheckResult(checkID)
        return if(phoneCheckResult != null) {
            Result.Success(phoneCheckResult)
        } else {
            Result.Error(Exception("HTTP Error getting phone check results"))
        }
    }

    @Throws(Exception::class)
    suspend fun checkWithTrace(url: String): KTraceInfo {
        Log.d("TruSDK", "Triggering checkWithTrace url $url")
        return sdk.checkWithTrace(url)
    }

    @Throws(Exception::class)
    suspend fun isReachable(): KReachabilityDetails? {
        Log.d("TruSDK", "Triggering isReachable")
        return sdk.isReachable()
    }

//    @Throws(Exception::class)
//    fun getJSON(): String? {
//        val baseURL = "https://tidy-crab-73.loca.lt/my-ip"
////        val truSdk = TruSDK.getInstance()
//        return truSdk.getJsonPropertyValue(baseURL, "ip_address")
//    }

    companion object {
        private const val TAG = "PhoneCheckActivity"
    }
}