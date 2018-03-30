package app

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.content.default
import io.ktor.content.file
import io.ktor.content.static
import io.ktor.content.staticRootFolder
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.html.respondHtml
import io.ktor.pipeline.PipelinePhase
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import io.ktor.util.hex
import kotlinx.html.*
import org.koin.Koin
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.startKoin
import org.mindrot.jbcrypt.BCrypt
import utils.KtorKoinLogger
import java.io.File

fun main(args: Array<String>) {
    val environment = commandLineEnvironment(args)
    Koin.logger = KtorKoinLogger(environment.log)
    startKoin(listOf(RepositoryModule))
    embeddedServer(Netty, environment).start()
}

fun Application.app() {
    install(CORS) { anyHost() }
    install(DefaultHeaders)
    install(CallLogging)
    install(Authentication) {
        form("topsecret") {
            validate {
                val hashed = BCrypt.hashpw("admin", BCrypt.gensalt())
                val youShallPass = BCrypt.checkpw(it.password, hashed)
                if (it.name == "admin" && youShallPass) UserIdPrincipal(hashed)
                else null
            }
            challenge = FormAuthChallenge.Redirect { call, _ ->
                call.sessions.clear<UserIdPrincipal>()
                "/hello"
            }
        }
    }
    install(Sessions) {
        cookie<UserIdPrincipal>("session") {
            transform(SessionTransportTransformerMessageAuthentication(hex("deadbeef")))
        }
    }
    intercept(ApplicationCallPipeline.Infrastructure) {
        log.debug("${call.sessions}")
        log.debug("${call.authentication.principal}")
    }
    intercept(ApplicationCallPipeline.Infrastructure) {
        call.sessions.get<UserIdPrincipal>()?.let { call.authentication.principal(it) }
    }
}

fun Application.hello() {
    routing {
        get("/hello") {
            if (call.authentication.principal != null) {
                call.respondRedirect("/admin")
                return@get
            }
            call.respondHtml {
            body {
                form {
                    method = FormMethod.post
                    action = "/login"
                    div {
                        label { +"User" }
                        input {
                            name = "user"
                        }
                        label { +"Password" }
                        input {
                            name = "password"
                            type = InputType.password
                        }
                    }
                    submitInput {

                    }
                }
            }
        } }
        authenticate("topsecret") {
            get("/admin") {
                call.respondHtml {
                    body {
                        h3 { +"Hello Admin" }
                        h2 { +"${call.sessions.get<UserIdPrincipal>()}" }
                        a {
                            href = "/logout"
                            button { +"Logout" }
                        }
                    }
                }
            }
            post("/login") {
                call.sessions.set(call.authentication.principal!! as UserIdPrincipal)
                call.respondRedirect("/admin")
            }
        }
        get("/logout") {
            call.sessions.clear<UserIdPrincipal>()
            call.respondRedirect("/hello")
        }
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
