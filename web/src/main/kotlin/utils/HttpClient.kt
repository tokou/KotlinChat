package utils

import utils.Fetcher.Method.*

object HttpClient {

    private val fetcher = Fetcher

    suspend fun <T> get(url: URL, body: String? = null, parse: (String) -> T): T? =
        fetcher.fetchJson(GET, url, body, parse)

    suspend fun post(url: URL, body: String) {
        fetcher.sendJson(POST, url, body)
    }

    suspend fun put(url: URL, body: String) {
        fetcher.sendJson(PUT, url, body)
    }

    suspend fun delete(url: URL) {
        fetcher.sendJson(DELETE, url, null)
    }

}

