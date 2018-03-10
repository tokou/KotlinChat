package utils

import chat.Message
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeTest {

    val str = """[
        |{"content":"Hello","author":"John","timestamp":"2018-03-12T12:00:01"},
        |{"content":"World","author":"Mick","timestamp":"2018-03-12T10:42:01"},
        |{"content":"React","author":"Tracy","timestamp":"2018-03-12T09:03:21"}
        |]""".trimMargin().replace("\n", "")

    val list = listOf(
        Message("Hello", "John", "2018-03-12T12:00:01".parseDate()),
        Message("World", "Mick", "2018-03-12T10:42:01".parseDate()),
        Message("React", "Tracy", "2018-03-12T09:03:21".parseDate())
    )

    @Test
    fun deserialization() {
        val listType = object : TypeToken<List<Message>>() {}.type
        val parsed = gson.fromJson<List<Message>>(str, listType)
        assertEquals(list, parsed)
    }

    @Test
    fun serialization() {
        val serialized = list.toJson()
        assertEquals(str, serialized)
    }
}
