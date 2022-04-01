package id.tru.kmm.phonecheckexample
import org.json.JSONObject

data class KTraceInfo(val trace: String, val debugInfo: KDebugInfo, val responseBody: JSONObject?)

