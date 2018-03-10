package utils

import chat.Message
import utils.DateTimeTestUtils.list
import utils.DateTimeTestUtils.str
import kotlinx.serialization.list
import kotlin.js.Date
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DateTimeTest {

    @Test
    fun deserialization() {
        val parsed = json.parse(Message.serializer().list, str)
        assertEquals(list, parsed)
    }

    @Test
    fun serialization() {
        val serialized = json.stringify(Message.serializer().list, list)
        assertEquals(str, serialized)
    }

    @Test
    fun equality() {
        val a = DateTime(Date(1234))
        val b = DateTime(Date(1234))
        assertEquals(a, b)
    }

    @Test
    fun inequality() {
        val a = DateTime(Date(4321))
        val b = DateTime(Date(1234))
        assertNotEquals(a, b)
    }

    @Test
    fun comparisonLower() {
        val a = DateTime(Date(4321))
        val b = DateTime(Date(1234))
        assertEquals(-1, b.compareTo(a))
    }

    @Test
    fun comparisonHigher() {
        val a = DateTime(Date(4321))
        val b = DateTime(Date(1234))
        assertEquals(1, a.compareTo(b))
    }

    @Test
    fun comparisonEquals() {
        val a = DateTime(Date(1234))
        val b = DateTime(Date(1234))
        assertEquals(0, b.compareTo(a))
    }
}
