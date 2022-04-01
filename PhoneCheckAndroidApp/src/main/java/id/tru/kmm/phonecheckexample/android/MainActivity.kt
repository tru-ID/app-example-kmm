package id.tru.kmm.phonecheckexample.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import id.tru.kmm.phonecheckexample.KReachabilityDetails
import id.tru.kmm.phonecheckexample.KTraceInfo
import id.tru.kmm.phonecheckexample.KmmTruSDK
import id.tru.kmm.phonecheckexample.Platform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity() {
    private val sdk = KmmTruSDK(Platform(this))

    companion object {
        private const val TAG = "AppActivity"
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: TruSDK is being initialised")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Hello"

        Log.d(TAG, "Before Coroutine")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d(TAG, "During Coroutine")
                val isReachable = sdk.isReachable()
                Log.d(TAG, "isReachable called")
                Log.d(TAG, "KReachability Details: $KReachabilityDetails")
                val isRequestOnMobileNetwork = sdk.openCheckURL("")
                //val traceInfo = sdk.checkWithTrace(URL("https://www.cnn.com"))
                launch(Dispatchers.Main) {
                    print("Calling results on main thread")
                    tv.text = "TruSDK call made"
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    Log.d(TAG, "Exception Coroutine")
                }
            }
        }
        Log.d(TAG, "After Coroutine")
    }
}
