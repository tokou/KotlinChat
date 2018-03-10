package utils

import chat.Message
import com.google.gson.reflect.TypeToken
import utils.DateTimeTestUtils.list
import utils.DateTimeTestUtils.str
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeTest {

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
