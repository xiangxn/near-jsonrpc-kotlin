package io.near.jsonrpc.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class RpcRequest<T>(
  val jsonrpc: String = "2.0",
  val id: Int = 1,
  val method: String,
  val params: T? = null
)

@Serializable
data class RpcResponse<T>(
  val jsonrpc: String = "2.0",
  val id: Int,
  val result: T? = null,
  val error: Any? = null
)

class JsonRpcClient(private val endpoint: String) {
  private val client = HttpClient {
    install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
  }

  suspend inline fun <reified T> call(method: String, params: Any?): T {
    val req = RpcRequest(method = method, params = params)
    val resp = client.post(endpoint) { setBody(req) }.body<RpcResponse<T>>()
    resp.error?.let { throw RuntimeException("RPC error: $it") }
    return resp.result ?: throw RuntimeException("Empty result")
  }
}
