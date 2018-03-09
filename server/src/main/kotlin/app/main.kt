package app

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.response.*
import io.ktor.routing.*
import utils.gson

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter(gson))
    }
    install(Routing) {
        get("/") {
            call.respondText("Hello, World from Ktor!")
        }
    }
}
