package chat

import utils.DateTime

data class Message(val content: String, val author: String, val timestamp: DateTime)
