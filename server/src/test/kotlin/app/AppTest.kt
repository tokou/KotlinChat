package app

import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.AutoCloseKoinTest
import utils.DateTimeTestUtils.list
import utils.DateTimeTestUtils.str
import utils.TestRepositoryModule
import utils.gson
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest : AutoCloseKoinTest() {

    @BeforeTest
    fun before() {
        startKoin(listOf(TestRepositoryModule))
    }

    @Test
    fun rootSendsHello() = withTestApplication(Application::hello) {
        with(handleRequest(HttpMethod.Get, "/hello")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello world", response.content)
        }
    }

    @Test
    fun stubbedMessages() = withTestApplication({
        app()
        messages()
    }) {
        with(handleRequest(HttpMethod.Get, "/messages") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(str, response.content)
        }
    }

    @Test
    fun postMessages() = withTestApplication({
        app()
        messages()
    }) {
        with(handleRequest(HttpMethod.Post, "/messages") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            body = gson.toJson(list[0])
        }) {
            assertEquals(HttpStatusCode.Created, response.status())
        }
    }

}
