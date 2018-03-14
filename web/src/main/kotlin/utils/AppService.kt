package utils

import chat.Message
import kotlinx.serialization.list

object AppService {

    private const val baseUrl = "http://localhost:8080/" // TODO move to some env
    private const val messagesUrl = baseUrl + "messages"

    suspend fun getMessages(): List<Message>? =
        HttpClient.get(messagesUrl) { json.parse(Message.serializer().list, it) }

    suspend fun postMessage(message: Message) {
        HttpClient.post(messagesUrl, json.stringify(message))
    }
}
