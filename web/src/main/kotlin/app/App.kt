package app

import chat.Message
import input.newMessage
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.await
import kotlinx.serialization.list
import chat.Message.Companion.serializer as messageSerializer
import org.w3c.fetch.Request
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import react.*
import react.dom.div
import thread.thread
import utils.DateTime
import utils.json
import kotlin.browser.window
import kotlin.js.Date
import kotlin.js.json

interface AppState : RState {
    var messages : List<Message>
    var intervalId : Int?
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        messages = emptyList()
    }

    override fun componentDidMount() {
        updateMessages()
        val intervalId = window.setInterval(this::updateMessages, 1000)
        setState { this.intervalId = intervalId }
    }

    override fun componentWillUnmount() {
        state.intervalId?.let { window.clearInterval(it) }
    }

    private fun updateMessages() {
        async {
            val fetched = fetchMessages()
            setState { messages = fetched }
        }
    }

    override fun RBuilder.render() {
        div {
            thread(messages = state.messages)
            newMessage({
                val now = DateTime(Date())
                postMessage(Message(it, "toto", now))
                updateMessages()
            })
        }
    }

    companion object {
        suspend fun fetchMessages(): List<Message> {
            val url = "http://localhost:8080/messages"
            val request = Request(url)
            val response = window.fetch(request).await()
            val text = response.text().await()
            return text.let { json.parse(messageSerializer().list, it) }
        }

        suspend fun postMessage(message: Message) {
            val url = "http://localhost:8080/messages"
            val request = Request(url, object : RequestInit {
                override var method: String? = "POST"
                override var body: dynamic = json.stringify(message)
                override var credentials: RequestCredentials? = "same-origin".asDynamic()
                override var headers: dynamic = json(
                    "Accept" to "application/json",
                    "Content-type" to "application/json")
            })
            window.fetch(request).await()
        }
    }
}

fun RBuilder.app() = child(App::class) {}
