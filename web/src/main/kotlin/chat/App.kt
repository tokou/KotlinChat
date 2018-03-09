package chat

import kotlin.browser.document

fun main(args: Array<String>) {
    document.addEventListener("DOMContentLoaded", {
        document.getElementById("content")!!.innerHTML = "Hello world"
    })
}
