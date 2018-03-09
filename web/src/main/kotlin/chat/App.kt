package chat

import react.*
import react.dom.*
import kotlin.browser.*

fun main(args: Array<String>) {
    console.log("It is alive!")
    document.addEventListener("DOMContentLoaded", {
        document.getElementById("content")?.apply { render(this) { app() } }
    })
}

fun RBuilder.app() = child(App::class) {}

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            +"Hello from React"
        }
    }
}
