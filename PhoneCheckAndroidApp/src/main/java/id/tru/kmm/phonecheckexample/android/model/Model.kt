package id.tru.kmm.phonecheckexample.android.model
import id.tru.kmm.phonecheckexample.Platform



import com.google.gson.annotations.SerializedName

data class PhoneCheckPost(
    @SerializedName("phone_number")
    val phone_number: String
)

data class PhoneCheck(
    @SerializedName("check_url")
    val check_url: String,
    @SerializedName("check_id")
    val check_id: String
)

data class PhoneCheckResult(
    @SerializedName("match")
    val match: Boolean,
    @SerializedName("check_id")
    val check_id: String
)