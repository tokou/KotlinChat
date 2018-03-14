package app

import chat.Message
import input.newMessage
import kotlinx.coroutines.experimental.async
import react.*
import react.dom.div
import thread.thread
import utils.AppService
import utils.DateTime
import utils.now
import kotlin.browser.window

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
            val fetched = AppService.getMessages() ?: emptyList()
            setState { messages = fetched }
        }
    }

    override fun RBuilder.render() {
        div {
            thread(messages = state.messages)
            newMessage({
                AppService.postMessage(Message(it, "toto", DateTime.now()))
                updateMessages()
            })
        }
    }
}

fun RBuilder.app() = child(App::class) {}
