package app

import chat.Message

interface MessageRepository {
    fun getMessages(): List<Message>
}

class MemoryMessageRepository : MessageRepository {

    private val messages = mutableListOf<Message>()

    override fun getMessages(): List<Message> = messages

    fun insertMessage(message: Message) {
        messages.add(message)
    }
}
