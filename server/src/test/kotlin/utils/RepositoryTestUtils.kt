package utils

import app.MessageRepository
import chat.Message
import org.koin.dsl.module.applicationContext

val TestRepositoryModule = applicationContext {
    bean<MessageRepository> { StubsMessageRepository() }
}

class StubsMessageRepository : MessageRepository {
    override fun getMessages(): List<Message> = DateTimeTestUtils.list
}
