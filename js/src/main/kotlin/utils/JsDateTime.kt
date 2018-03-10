package utils

import kotlin.js.Date

actual data class DateTime(internal val date: Date) : Comparable<DateTime> {

    actual fun toDateFormatString(): String = date.toISOString()

    override operator fun compareTo(other: DateTime) = compareValuesBy(
        date, other.date, Date::getFullYear, Date::getMonth, Date::getDay, Date::getHours, Date::getMinutes, Date::getSeconds
    )
}

actual fun String.parseDate() = DateTime(Date(Date.parse(this)))

fun DateTime.toDisplayTime() = date.toLocaleTimeString("fr-FR")
