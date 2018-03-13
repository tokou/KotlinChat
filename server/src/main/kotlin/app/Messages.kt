package app

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.routing
import org.koin.ktor.ext.inject

@Location("/messages")
class Messages

fun Application.messages() {

    val repository by inject<MessageRepository>()

    routing {
        get<Messages> {
            call.respond(repository.getMessages())
        }
    }
}
