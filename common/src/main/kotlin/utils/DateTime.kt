package utils

expect class DateTime : Comparable<DateTime> {
    companion object {}

    fun toDateFormatString(): String
}

expect fun String.parseDate(): DateTime

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
