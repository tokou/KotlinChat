package utils

expect class DateTime : Comparable<DateTime> {
    fun toDateFormatString(): String
}

expect fun String.parseDate(): DateTime

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
