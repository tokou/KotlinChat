package app

import chat.Message
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.response.*
import io.ktor.routing.*
import utils.parseDate
import utils.gson

fun Application.main() {
    install(CORS) {
        anyHost()
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter(gson))
    }
    install(Routing) {
        get("/") {
            call.respondText("Hello world")
        }
        get("/messages") {
            call.respond(listOf(
                Message("Hello", "John", "2018-03-12T12:00:01".parseDate()),
                Message("World", "Mick", "2018-03-12T10:42:01".parseDate()),
                Message("React", "Tracy", "2018-03-12T09:03:21".parseDate())
            ))
        }
    }
}
