package chat

import utils.DateTime
import kotlinx.serialization.Serializable

@Serializable
data class Message(val content: String, val author: String, val timestamp: DateTime)
