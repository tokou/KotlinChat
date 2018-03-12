package chat

import app.Hello
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route

fun Route.hello() {
    get<Hello> {
        call.respondText("Hello world")
    }
}
