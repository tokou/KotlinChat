package app

import io.ktor.application.*
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import utils.DateTimeTestUtils.str

class AppTest {

    @Test
    fun rootSendsHello() = withTestApplication(Application::main) {
        with(handleRequest(HttpMethod.Get, "/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello world", response.content)
        }
    }

    @Test
    fun stubbedMessages() = withTestApplication(Application::main) {
        with(handleRequest(HttpMethod.Get, "/messages") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(str, response.content)
        }
    }
}
