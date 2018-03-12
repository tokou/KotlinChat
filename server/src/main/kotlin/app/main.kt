package app

import chat.hello
import chat.messages
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import utils.gson

@Location("/messages")
class Messages

@Location("/hello")
class Hello

fun Application.main() {
    install(CORS) {
        anyHost()
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter(gson))
    }
    install(Locations)
    install(Routing) {
        hello()
        messages()
    }
}
