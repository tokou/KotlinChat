package thread

import chat.Message
import chat.toDisplayString
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.li
import react.dom.ul

interface ThreadProps : RProps {
    var messages: List<Message>
}

class Thread : RComponent<ThreadProps, RState>() {
    override fun RBuilder.render() {
        console.log(props)
        ul {
            props.messages.forEach {
                li {
                    +it.toDisplayString()
                }
            }
        }
    }
}

fun RBuilder.thread(messages: List<Message>) = child(Thread::class) { attrs.messages = messages }
