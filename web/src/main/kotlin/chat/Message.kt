package chat

import utils.toDisplayTime

fun Message.toDisplayString() = "[$author @ ${timestamp.toDisplayTime()}]: $content"
