package utils

import kotlin.js.Date

actual data class DateTime(internal val date: Date) : Comparable<DateTime> {

    actual fun toDateFormatString(): String = date.toISOString().dropLast(5) // '.000Z'

    override operator fun compareTo(other: DateTime) = compareValuesBy(date, other.date, Date::getTime)

    override fun equals(other: Any?): Boolean = when (other) {
        is DateTime -> compareTo(other) == 0
        else -> false
    }

    override fun hashCode(): Int = date.hashCode()
}

actual fun String.parseDate() = DateTime(Date(Date.parse(this)))

fun DateTime.toDisplayTime() = date.toLocaleTimeString("fr-FR")
