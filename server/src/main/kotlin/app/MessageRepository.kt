package app

import chat.Message

interface MessageRepository {
    fun getMessages(): List<Message>
    fun insertMessage(message: Message)
}

class MemoryMessageRepository : MessageRepository {

    private val messages = mutableListOf<Message>()

    override fun getMessages(): List<Message> = messages

    override fun insertMessage(message: Message) {
        messages.add(message)
    }
}
