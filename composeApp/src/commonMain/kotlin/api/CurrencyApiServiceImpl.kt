package api

import api.model.ApiResponse
import api.model.Currency
import api.model.RequestState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CurrencyApiServiceImpl(

) : CurrencyApiService {

    companion object {
        const val ENDPOINT = "https://api.currencyapi.com/v3/latest/"
        const val API_KEY = "cur_live_AdYFAvupQJXc5LuNuNe7RAxOIr0aJTwm56RDOXgM"
    }


    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(DefaultRequest) {
            headers {
                append("apiKey", API_KEY)
            }
        }
    }

    override suspend fun getLatestExchangeRates(): RequestState<List<Currency>> {
        return runCatching {
            httpClient.get(ENDPOINT)
        }.fold(
            onSuccess = { response ->
                if (response.status.value == 200) {
                    val apiResponse = Json.decodeFromString<ApiResponse>(response.body())
                    RequestState.Success(data = apiResponse.data.values.toList())
                } else {
                    RequestState.Error(message = "네트워크 오류 ${response.status}")
                }
            },
            onFailure = { error ->
                RequestState.Error(message = "An exception occurred: ${error.message}")
            }
        )
    }
}