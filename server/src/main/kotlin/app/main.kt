package app

import chat.hello
import chat.messages
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.content.default
import io.ktor.content.file
import io.ktor.content.static
import io.ktor.content.staticRootFolder
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import io.ktor.routing.routing
import utils.gson
import java.io.File

@Location("/messages")
class Messages

@Location("/hello")
class Hello

fun Application.app() {
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter(gson))
    }
    install(Locations)
    install(Routing) {
        hello()
        messages()
    }
}

fun Application.main() {
    install(CORS) {
        anyHost()
    }
    install(DefaultHeaders)
    install(CallLogging)
}

fun Application.web() {
    routing {
        static("app") {
            staticRootFolder = File("/Users/tarek/Sources/github/KotlinChat/web/build/bundle")
            default("index.html")
            file("main.bundle.js")
        }
    }
}
