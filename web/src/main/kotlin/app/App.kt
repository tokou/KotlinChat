package app

import chat.Message
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import thread.thread
import utils.parseDate

interface AppState : RState {
    var messages : List<Message>
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        messages = listOf(
            Message("Hello", "John", "2018-03-12T12:00:01".parseDate()),
            Message("World", "Mick", "2018-03-12T10:42:01".parseDate()),
            Message("React", "Tracy", "2018-03-12T09:03:21".parseDate())
        )
    }

    override fun RBuilder.render() {
        div {
            thread(messages = state.messages)
        }
    }
}

fun RBuilder.app() = child(App::class) {}
