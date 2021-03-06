package id.tru.kmm.phonecheckexample

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class APIService {
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(SERVER_BASE_URL)
        }
    }
    //Temporary
    private val SERVER_BASE_URL = "https://cfc7-2a00-23c7-8589-8d01-3569-af2-f107-5b66.ngrok.io"

    // POST /check
    // This method will supposedly seriliase to post body as well as deserialise the response to correct type
    @Throws(Exception::class)
    suspend fun getPhoneCheck(user: PhoneCheckPost): PhoneCheck? {//: Response<PhoneCheck>
        val response: HttpResponse = try {
            client.post("/check") {
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        } catch (cause: ResponseException) {
            println("cause: $cause")
            cause.response
        }
        return response.body()
    }

    // GET /check_status, query value = "check_id"
    @Throws(Exception::class)
    suspend fun getPhoneCheckResult(checkId: String): PhoneCheckResult? {//: Response<PhoneCheckResult>
        val response: HttpResponse = try {
            client.get("/check_status") {
                contentType(ContentType.Application.Json)
                parameter("check_id", checkId)
            }
        } catch (cause: ResponseException) {
        println("cause: $cause")
            cause.response
        }
        println("GET check_status -> ${response.request.url}")
        return response.body()
    }
}