package input

import kotlinx.coroutines.experimental.async
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onSubmitFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import utils.usingValue

interface NewMessageProps : RProps {
    var onNewMessage: suspend (input: String) -> Unit
}

interface NewMessageState : RState {
    var value: String
}

class NewMessage : RComponent<NewMessageProps, NewMessageState>() {

    override fun NewMessageState.init() {
        value = ""
    }

    override fun RBuilder.render() {
        form {
            val submit: (Event) -> Unit = { this@NewMessage.sendMessage() }
            attrs {
                onSubmitFunction = submit
            }
            div {
                label {
                    attrs.htmlFor = "message"
                    +"Message"
                }
                input(InputType.text) {
                    attrs {
                        id = "message"
                        name = "message"
                        placeholder = "My message"
                        onChangeFunction = usingValue {
                            setState {
                                value = it
                            }
                        }
                    }
                }
            }
            button {
                attrs {
                    onClickFunction = submit
                    type = ButtonType.reset
                }
                +"Send"
            }
        }
    }

    private fun sendMessage() {
        async {
            props.onNewMessage(state.value)
            setState {
                value = ""
            }
        }
    }
}

fun RBuilder.newMessage(onNewMessage: suspend (input: String) -> Unit) =
    child(NewMessage::class) { attrs.onNewMessage = onNewMessage }
