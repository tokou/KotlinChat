package app

import chat.Message
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.await
import org.w3c.fetch.Request
import react.*
import react.dom.div
import thread.thread
import utils.json
import kotlin.browser.window

interface AppState : RState {
    var messages : List<Message>
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        messages = emptyList()
    }

    override fun componentDidMount() {
        updateMessages()
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
        }
    }

    companion object {
        suspend fun fetchMessages(): List<Message> {
            val url = "http://localhost:8080/messages"
            val request = Request(url)
            val response = window.fetch(request).await()
            val text = response.text().await()
            return text.let { json.parse(it) }
        }
    }
}

fun RBuilder.app() = child(App::class) {}
