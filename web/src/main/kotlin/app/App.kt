package app

import chat.Message
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import thread.thread
import kotlin.js.Date

interface AppState : RState {
    var messages : List<Message>
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        messages = listOf(
                Message("Hello", "John", Date()),
                Message("World", "Mick", Date()),
                Message("React", "Tracy", Date())
        )
    }

    override fun RBuilder.render() {
        div {
            thread(messages = state.messages)
        }
    }
}

fun RBuilder.app() = child(App::class) {}
