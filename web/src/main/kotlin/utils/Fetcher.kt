package utils

import kotlinx.coroutines.experimental.await
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import org.w3c.fetch.SAME_ORIGIN
import kotlin.browser.window
import kotlin.js.json

typealias URL = String

object Fetcher {
    enum class Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    suspend fun sendJson(method: Method, url: URL, body: String?): Response? {
        val request = object : RequestInit {
            override var method: String? = method.name
            override var body: dynamic = body?.let { body }
            override var credentials: RequestCredentials? = RequestCredentials.SAME_ORIGIN
            override var headers: dynamic = json(
                "Accept" to "application/json",
                "Content-type" to "application/json"
            )
        }
        return window.fetch(url, request).catch { null }.await()
    }

    suspend fun <T> fetchJson(method: Method, url: URL, body: String?, parse: (String) -> T): T? {
        val response: Response? = sendJson(method, url, body)
        return response?.text()?.await()?.let { parse(it) }
    }
}
