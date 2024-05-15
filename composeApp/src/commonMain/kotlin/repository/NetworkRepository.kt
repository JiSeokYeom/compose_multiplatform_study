package repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class NetworkRepository(
    private val httpClient: HttpClient
) {

   /* fun getProductList(): Flow<NetWorkResult<ApiResponse?>> {
        return toResultFlow {
            val response = httpClient.get("api url").body<ApiResponse>()
            NetWorkResult.Success(response)
        }
    }*/
}