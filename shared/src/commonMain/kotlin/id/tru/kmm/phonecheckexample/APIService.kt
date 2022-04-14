package id.tru.kmm.phonecheckexample

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*



class APIService {

    private val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(SERVER_BASE_URL)
        }
    }

    //Temporary
    private val SERVER_BASE_URL = "https://851c-2a00-23c7-8589-8d01-39f4-a079-4a1-2daa.ngrok.io"

    // POST /check
    // This method will supposedly seriliase to post body as well as deserialise the response to correct type
//    @Throws(Exception::class)
    suspend fun getPhoneCheck(user: PhoneCheckPost): PhoneCheck? {//: Response<PhoneCheck>
        val response: HttpResponse = client.post("/check") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return response.body()
    }

    // GET /check_status, query value = "check_id"
//    @Throws(Exception::class)
    suspend fun getPhoneCheckResult(checkId: String): PhoneCheckResult? {//: Response<PhoneCheckResult>
        val response: HttpResponse = client.get("/check_status") {
            contentType(ContentType.Application.Json)
            parameter("check_id", checkId)
        }
        println("GET check_status -> ${response.request.url}")
        return response.body()
    }
}