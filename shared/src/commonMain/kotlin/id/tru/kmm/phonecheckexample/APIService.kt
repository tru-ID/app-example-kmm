package id.tru.kmm.phonecheckexample

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class APIService {

    private val client = HttpClient(CIO){
        install(ContentNegotiation)
        defaultRequest {
            url(SERVER_BASE_URL)
        }
    }

    //Temporary
    private val SERVER_BASE_URL = "https://74b3-2a00-23c7-8589-8d01-2d6f-82cd-46df-5b07.ngrok.io"

    // POST /check
    // This method will supposedly seriliase to post body as well as deserialise the response to correct type
    suspend fun getPhoneCheck(user: PhoneCheckPost): PhoneCheck? {//: Response<PhoneCheck>
        val response: HttpResponse = client.post("check") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        println("POST check -> ${response.request.url}")
        return response.body()
    }

    // GET /check_status, query value = "check_id"
    suspend fun getPhoneCheckResult(checkId: String): PhoneCheckResult? {//: Response<PhoneCheckResult>
        val response: HttpResponse = client.get("check_status") {
            contentType(ContentType.Application.Json)
            parameter("check_id", checkId)
        }
        println("GET check_status -> ${response.request.url}")
        return response.body()
    }
}