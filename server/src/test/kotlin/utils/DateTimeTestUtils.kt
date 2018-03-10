package utils

import chat.Message

// Duplicated from :common
// Could not find a way to use it in tests without duplicating it
object DateTimeTestUtils {

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
}
