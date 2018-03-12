package chat

import app.Messages
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import utils.parseDate

fun Route.messages() {
    get<Messages> {
        call.respond(
            listOf(
                Message("Hello", "John", "2018-03-12T12:00:01".parseDate()),
                Message("World", "Mick", "2018-03-12T10:42:01".parseDate()),
                Message("React", "Tracy", "2018-03-12T09:03:21".parseDate())
            )
        )
    }
}
