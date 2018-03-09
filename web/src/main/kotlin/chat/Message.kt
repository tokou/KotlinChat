package chat

import kotlin.js.Date

data class Message(val content: String, val author: String, val timestamp: Date) {
    override fun toString() = "[$author @ ${timestamp.toLocaleTimeString("fr-FR")}]: $content"
}
