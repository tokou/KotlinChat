package app

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.content.default
import io.ktor.content.file
import io.ktor.content.static
import io.ktor.content.staticRootFolder
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.locations.Locations
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.Koin
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.startKoin
import utils.KtorKoinLogger
import utils.gson
import java.io.File

fun main(args: Array<String>) {
    val environment = commandLineEnvironment(args)
    Koin.logger = KtorKoinLogger(environment.log)
    startKoin(listOf(RepositoryModule))
    embeddedServer(Netty, environment).start()
}

fun Application.app() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter(gson))
    }
    install(Locations)
}

fun Application.hello() {
    routing {
        get("/hello") { call.respondText("Hello world") }
    }
}

fun Application.web() {
    routing {
        static("/") {
            staticRootFolder = File("../web/build/bundle/")
            file("main.bundle.js")
            default("index.html")
        }
    }
}

val RepositoryModule = applicationContext {
    bean<MessageRepository> { MemoryMessageRepository() }
}
