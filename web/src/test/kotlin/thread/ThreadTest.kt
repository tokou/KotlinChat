package thread

import external.enzyme.shallowElement
import utils.ReactTest
import kotlin.test.Test
import utils.DateTimeTestUtils.list
import kotlin.test.assertEquals

class ThreadTest : ReactTest {

    @Test
    fun threadLength() {
        val element = shallowElement { thread(list) }
        assertEquals(list.size, element.find("li").length)
    }
}
