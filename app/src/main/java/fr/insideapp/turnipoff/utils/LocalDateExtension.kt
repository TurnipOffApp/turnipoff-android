package fr.insideapp.turnipoff.utils

import fr.insideapp.turnipoff.network.TheMovieDBClient
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private var dtf: DateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

fun LocalDate.formatForService(): String {
    return this.format(TheMovieDBClient.formatter)
}

fun LocalDate.formatForDisplay(): String {
    return this.format(dtf)
}